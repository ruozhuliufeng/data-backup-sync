package tech.msop.data.service.task;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import tech.msop.data.entity.system.TaskEntity;
import tech.msop.data.enums.TaskStatusEnum;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时任务抽象类，提供基本的功能
 *
 * @author ruozhuliufeng
 */
@Component
@Slf4j
public abstract class AbstractSchedulerTask implements SchedulerTaskJob {
    @Resource
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    /**
     * 执行任务
     */
    @Override
    public void executeTask() {
        List<TaskEntity> taskList = getTaskList();
        if (CollectionUtil.isNotEmpty(taskList)) {
            taskList.forEach(task -> {
                String cron = task.getTaskCron();
                // 执行相关业务
                Runnable taskRunnable = () -> {
                    execute(task);
                };
                Trigger trigger = triggerContext -> {
                    CronTrigger cronTrigger;
                    try {
                        cronTrigger = new CronTrigger(cron);
                        task.setTaskStatus(TaskStatusEnum.PROCEED.getStatus());
                        updateTaskStatus(task);
                        return cronTrigger.nextExecutionTime(triggerContext);
                    } catch (Exception e) {
                        log.error("CRON 表达式异常,已启用默认的CRON表达式");
                        cronTrigger = new CronTrigger(getDefaultCron());
                        task.setTaskStatus(TaskStatusEnum.STOP.getStatus());
                        updateTaskStatus(task);
                        return cronTrigger.nextExecutionTime(triggerContext);
                    }
                };
                threadPoolTaskScheduler.schedule(taskRunnable, trigger);
            });
        }
    }

    /**
     * 获取数据库中的任务列表
     *
     * @return 任务列表
     */
    protected abstract List<TaskEntity> getTaskList();

    /**
     * 执行任务
     *
     * @param task 任务详情
     */
    protected abstract void execute(TaskEntity task);

    /**
     * 获取默认的表达式，当配置的CRON表达式异常时，执行默认的表达式
     *
     * @return CRON表达式
     */
    protected abstract String getDefaultCron();

    /**
     * 更新任务状态
     *
     * @param task 状态
     */
    protected abstract void updateTaskStatus(TaskEntity task);
}

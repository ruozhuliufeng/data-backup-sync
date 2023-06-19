package tech.msop.data.service.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;
import tech.msop.data.entity.system.TaskEntity;
import tech.msop.data.enums.TaskStatusEnum;

/**
 * 定时任务抽象类，提供基本的功能
 *
 * @author ruozhuliufeng
 */
@Slf4j
public abstract class AbstractSchedulerTask implements SchedulerTaskJob {
    /**
     * 执行任务
     */
    @Override
    public void executeTask(TaskEntity task) {
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
    }

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

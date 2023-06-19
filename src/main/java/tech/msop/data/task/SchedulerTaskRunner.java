package tech.msop.data.task;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import tech.msop.core.tool.utils.CollectionUtil;
import tech.msop.data.config.CronTaskRegister;
import tech.msop.data.entity.system.TaskEntity;
import tech.msop.data.enums.TaskTypeEnum;
import tech.msop.data.service.system.TaskService;
import tech.msop.data.service.task.impl.CategorySyncScheduler;
import tech.msop.data.service.task.impl.DatabaseSyncScheduler;
import tech.msop.data.service.task.impl.FileSyncScheduler;

import java.util.List;

/**
 * 项目启动完成后，加载数据库里状态正常的定时任务
 */
@Component
@Order(1)
@Slf4j
@AllArgsConstructor
public class SchedulerTaskRunner implements CommandLineRunner {
    private final CronTaskRegister cronTaskRegister;
    private final TaskService taskService;

    @Override
    public void run(String... args) throws Exception {
        log.info("开始加载定时任务....");

        // 获取数据库中状态正常的定时任务
        List<TaskEntity> taskList = taskService.list(Wrappers.<TaskEntity>query().eq("status", "1")
                .eq("is_deleted", 0)
        );
        if (!CollectionUtil.isEmpty(taskList)) {
            taskList.forEach(task -> {
                SchedulingRunnable scheduledTask = null;
                if (TaskTypeEnum.FILE_SYNC.getType() == task.getTaskType()) {

                    log.info("添加文件同步");
                    scheduledTask = new SchedulingRunnable(FileSyncScheduler.class,task);
                } else if (TaskTypeEnum.CATEGORY_SYNC.getType() == task.getTaskType()) {
                    // 文件夹同步
                    log.info("添加文件夹同步任务");
                    scheduledTask = new SchedulingRunnable(CategorySyncScheduler.class,task);
                } else {
                    // 数据库同步
                    log.info("添加数据库同步任务");
                    scheduledTask = new SchedulingRunnable(DatabaseSyncScheduler.class,task);
                }
                if (!ObjectUtil.isEmpty(scheduledTask)){
                    cronTaskRegister.addCronTask(scheduledTask,task.getTaskCron());
                }
            });
            log.info("定时任务已加载完毕!");
        }
    }
}

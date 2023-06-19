package tech.msop.data.service.task;

import tech.msop.data.entity.system.TaskEntity;

/**
 * 定时任务接口
 *
 * @author ruozhuliufeng
 */
public interface SchedulerTaskJob {
    /**
     * 执行任务
     */
    void executeTask(TaskEntity task);
}

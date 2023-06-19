package tech.msop.data.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;
import tech.msop.data.task.ScheduledTask;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 定时任务注册类<br/>
 * 用于增加、删除定时任务
 *
 * @author ruozhuliufeng
 */
@Component
@AllArgsConstructor
public class CronTaskRegister implements DisposableBean {
    private final Map<Runnable, ScheduledTask> scheduledTasks = new ConcurrentHashMap<>(16);
    private final TaskScheduler taskScheduler;

    /**
     * 新增Cron任务
     *
     * @param task           任务
     * @param cronExpression CRON表达式
     */
    public void addCronTask(Runnable task, String cronExpression) {
        addCronTask(new CronTask(task,cronExpression));
    }

    public void addCronTask(CronTask cronTask) {
        if (cronTask != null) {
            Runnable task = cronTask.getRunnable();
            if (this.scheduledTasks.containsKey(task)) {
                removeCronTask(task);
            }
            this.scheduledTasks.put(task,scheduledTask(cronTask));
        }
    }

    public ScheduledTask scheduledTask(CronTask cronTask){
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.future = this.taskScheduler.schedule(cronTask.getRunnable(),cronTask.getTrigger());
        return scheduledTask;
    }

    public void removeCronTask(Runnable task) {
        ScheduledTask scheduledTask = this.scheduledTasks.remove(task);
        if (scheduledTask != null) {
            scheduledTask.cancel();
        }
    }

    @Override
    public void destroy() throws Exception {
        this.scheduledTasks.values().forEach(ScheduledTask::cancel);
        this.scheduledTasks.clear();
    }
}

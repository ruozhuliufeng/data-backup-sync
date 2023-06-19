package tech.msop.data.task;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tech.msop.core.tool.utils.SpringUtil;
import tech.msop.data.entity.system.TaskEntity;
import tech.msop.data.service.task.SchedulerTaskJob;

import java.util.HashMap;
import java.util.Objects;

/**
 * Runnable接口实现,用来执行指定Bean的方法
 *
 * @author ruozhuliufeng
 */
@Slf4j
@AllArgsConstructor
public class SchedulingRunnable implements Runnable {
    private Class<? extends SchedulerTaskJob> clazz;
    private TaskEntity task;
    @Override
    public void run() {
        log.info("定时任务开始执行 - ClassName:{},- Task:{}",clazz.getName(),task);
        long startTime = System.currentTimeMillis();
        try {
            SchedulerTaskJob job = SpringUtil.getBean(clazz);
            job.executeTask(task);
            long cost = System.currentTimeMillis() - startTime;
            log.info("定时任务执行结束,耗时:{}毫秒",cost);
        }catch (Exception e){
            log.error("定时任务执行异常 - 异常信息:{}",e.getMessage());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        SchedulingRunnable that = (SchedulingRunnable) obj;
        return Objects.equals(this.task.getId(), that.task.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(clazz,task.getId());
    }
}

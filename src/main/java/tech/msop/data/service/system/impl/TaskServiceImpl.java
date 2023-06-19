package tech.msop.data.service.system.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.msop.core.tool.utils.ObjectUtil;
import tech.msop.data.config.CronTaskRegister;
import tech.msop.data.entity.system.Option;
import tech.msop.data.entity.system.TaskEntity;
import tech.msop.data.enums.StorageTypeEnum;
import tech.msop.data.enums.TaskStatusEnum;
import tech.msop.data.enums.TaskTypeEnum;
import tech.msop.data.mapper.TaskMapper;
import tech.msop.data.service.system.TaskService;
import tech.msop.data.service.task.impl.CategorySyncScheduler;
import tech.msop.data.service.task.impl.DatabaseSyncScheduler;
import tech.msop.data.service.task.impl.FileSyncScheduler;
import tech.msop.data.task.SchedulingRunnable;
import tech.msop.mybatis.service.impl.SuperServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl extends SuperServiceImpl<TaskMapper, TaskEntity>
        implements TaskService {
    private final CronTaskRegister cronTaskRegister;

    @Override
    public List<Option> queryStorageOptionList() {
        // 新增父级选项
        List<Option> parentOptionList = new ArrayList<>();
        for (StorageTypeEnum typeEnum : StorageTypeEnum.values()) {
            if (typeEnum.getType() != StorageTypeEnum.DEFAULT.getType()) {
                Option option = new Option();
                option.setPid(0L);
                option.setId((long) typeEnum.getType());
                option.setName(typeEnum.getMsg());
                parentOptionList.add(option);
            }
        }
        List<Option> list = baseMapper.queryStorageOptionList();
        list.addAll(parentOptionList);
        return list;
    }

    /**
     * 新增任务处理
     *
     * @param task 任务信息
     */
    @Override
    public void saveTask(TaskEntity task) {
        // 新增任务
        boolean result = this.save(task);
        // 新增任务成功，将其添加到定时任务表达式中
        if (result) {
            SchedulingRunnable scheduledTask = getRunnable(task);
            if (!ObjectUtil.isEmpty(scheduledTask)) {
                cronTaskRegister.addCronTask(scheduledTask, task.getTaskCron());
            }
        }
    }

    /**
     * 更新任务处理
     *
     * @param task 任务信息
     */
    @Override
    public void updateTask(TaskEntity task) {
        this.updateById(task);
        // 获取任务信息
        TaskEntity taskEntity = this.getById(task.getId());
        // 获取定时任务
        SchedulingRunnable scheduledTask = getRunnable(task);
        // 删除原有的定时任务
        if (!ObjectUtil.isEmpty(scheduledTask)) {
            cronTaskRegister.removeCronTask(scheduledTask);
        }
        // 如果状态为进行中，添加新的定时任务
        if (TaskStatusEnum.PROCEED.getStatus() == taskEntity.getStatus()) {
            cronTaskRegister.addCronTask(scheduledTask, task.getTaskCron());
        }
    }

    /**
     * 新增或更新任务信息
     *
     * @param task 任务信息
     */
    @Override
    public void submit(TaskEntity task) {
        // 获取任务信息
        TaskEntity taskEntity = this.getById(task.getId());
        // 获取定时任务
        SchedulingRunnable scheduledTask = getRunnable(task);
        // 删除原有的定时任务
        if (!ObjectUtil.isEmpty(scheduledTask)) {
            cronTaskRegister.removeCronTask(scheduledTask);
        }
        // 如果状态为进行中，添加新的定时任务
        if (TaskStatusEnum.PROCEED.getStatus() == taskEntity.getStatus()) {
            cronTaskRegister.addCronTask(scheduledTask, task.getTaskCron());
        }
    }

    /**
     * 根据ID列表移除任务信息
     *
     * @param idList 任务ID列表
     */
    @Override
    public void removeByIdList(List<Long> idList) {
        List<TaskEntity> taskList = this.listByIds(idList);
        taskList.forEach(task -> {
            // 获取定时任务
            SchedulingRunnable scheduledTask = getRunnable(task);
            // 删除定时任务
            if (!ObjectUtil.isEmpty(scheduledTask)) {
                cronTaskRegister.removeCronTask(scheduledTask);
            }
        });
    }


    /**
     * 更新任务ID状态
     *
     * @param id     任务ID
     * @param status 更新后的状态
     */
    @Override
    public void changeStatus(Long id, Integer status) {
        TaskEntity task = this.getById(id);
        task.setTaskStatus(status);
        this.updateTask(task);
    }


    /**
     * 根据任务类型获取Runnable
     *
     * @param task 任务信息
     * @return 定时任务
     */
    private SchedulingRunnable getRunnable(TaskEntity task) {
        SchedulingRunnable schedulingRunnable = null;
        TaskTypeEnum typeEnum = TaskTypeEnum.of(task.getTaskType());
        if (!ObjectUtil.isEmpty(typeEnum)) {
            switch (typeEnum) {
                case FILE_SYNC:
                    schedulingRunnable = new SchedulingRunnable(FileSyncScheduler.class, task);
                    break;
                case CATEGORY_SYNC:
                    schedulingRunnable = new SchedulingRunnable(CategorySyncScheduler.class, task);
                    break;
                case DATABASE_SYNC:
                    schedulingRunnable = new SchedulingRunnable(DatabaseSyncScheduler.class, task);
                    break;
                default:
                    break;
            }
        }
        return schedulingRunnable;
    }
}

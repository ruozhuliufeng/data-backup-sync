package tech.msop.data.service.system;

import tech.msop.data.entity.system.Option;
import tech.msop.data.entity.system.TaskEntity;
import tech.msop.mybatis.service.ISuperService;

import java.util.List;

public interface TaskService extends ISuperService<TaskEntity> {
    /**
     * 获取云端存储的组合下拉选项
     *
     * @return 云端存储类别
     */
    List<Option> queryStorageOptionList();

    /**
     * 新增任务处理
     *
     * @param task 任务信息
     */
    void saveTask(TaskEntity task);

    /**
     * 更新任务处理
     *
     * @param task 任务信息
     */
    void updateTask(TaskEntity task);

    /**
     * 新增或更新任务信息
     *
     * @param task 任务信息
     */
    void submit(TaskEntity task);

    /**
     * 根据ID列表移除任务信息
     *
     * @param idList 任务ID列表
     */
    void removeByIdList(List<Long> idList);

    /**
     * 更新任务ID状态
     *
     * @param id     任务ID
     * @param status 更新后的状态
     */
    void changeStatus(Long id, Integer status);
}

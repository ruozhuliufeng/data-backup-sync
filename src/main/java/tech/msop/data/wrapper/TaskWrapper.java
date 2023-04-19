package tech.msop.data.wrapper;

import lombok.AllArgsConstructor;
import tech.msop.core.tool.utils.BeanUtil;
import tech.msop.data.entity.system.TaskEntity;
import tech.msop.data.enums.StorageTypeEnum;
import tech.msop.data.enums.TaskTypeEnum;
import tech.msop.data.utils.DatabaseUtil;
import tech.msop.data.utils.StorageUtil;
import tech.msop.data.vo.TaskVO;
import tech.msop.mybatis.support.BaseEntityWrapper;

import java.util.Objects;

/**
 * 视图类包装
 *
 * @author ruozhuliufeng
 */
@AllArgsConstructor
public class TaskWrapper extends BaseEntityWrapper<TaskEntity, TaskVO> {

    public static TaskWrapper build(){
        return new TaskWrapper();
    }

    @Override
    public TaskVO entityVO(TaskEntity entity) {
        TaskVO taskVO = Objects.requireNonNull(BeanUtil.copy(entity, TaskVO.class));
        StorageTypeEnum storageTypeEnum = StorageTypeEnum.of(taskVO.getStorageType());
        taskVO.setStorageName(storageTypeEnum.getMsg());
        TaskTypeEnum taskTypeEnum = TaskTypeEnum.of(taskVO.getTaskType());
        taskVO.setTaskTypeName(taskTypeEnum.getMsg());
        String storageName = StorageUtil.getStorageName(entity);
        taskVO.setStorageName(storageName);
        String databaseName = DatabaseUtil.getDatabaseNameByTask(entity);
        taskVO.setDatabaseName(databaseName);
        return taskVO;
    }
}

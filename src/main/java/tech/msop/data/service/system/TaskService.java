package tech.msop.data.service.system;

import tech.msop.data.entity.system.Option;
import tech.msop.data.entity.system.TaskEntity;
import tech.msop.mybatis.service.ISuperService;

import java.util.List;

public interface TaskService extends ISuperService<TaskEntity> {
    /**
     * 获取云端存储的组合下拉选项
     * @return 云端存储类别
     */
    List<Option> queryStorageOptionList();
}

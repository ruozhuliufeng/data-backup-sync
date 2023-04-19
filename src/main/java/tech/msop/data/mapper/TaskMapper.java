package tech.msop.data.mapper;

import tech.msop.data.entity.system.Option;
import tech.msop.data.entity.system.TaskEntity;
import tech.msop.mybatis.mapper.SuperMapper;

import java.util.List;

/**
 * 任务相关Mapper
 */
public interface TaskMapper extends SuperMapper<TaskEntity> {
    /**
     * 获取云端存储列表
     */
    List<Option> queryStorageOptionList();
}

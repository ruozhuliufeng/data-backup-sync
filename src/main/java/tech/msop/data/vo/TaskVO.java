package tech.msop.data.vo;

import lombok.Getter;
import lombok.Setter;
import tech.msop.data.entity.system.TaskEntity;

/**
 * 页面展示 VO
 *
 * @author ruozhuliufeng
 */
@Getter
@Setter
public class TaskVO extends TaskEntity {
    /**
     * 云端存储名
     */
    private String storageName;
    /**
     * 数据库标识
     */
    private String databaseName;
    /**
     * 任务类型名称
     */
    private String taskTypeName;
}

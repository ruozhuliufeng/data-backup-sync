package tech.msop.data.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.msop.data.common.BasePageRequest;

/**
 * 数据库查询条件
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DatabaseQueryVO extends BasePageRequest {
    /**
     * 数据库名称
     */
    private String name;
    /**
     * 数据库类型
     */
    private Integer type;
}

package tech.msop.data.vo;

import lombok.Data;
import tech.msop.data.entity.BasePageRequest;

/**
 * 数据库查询条件
 */
@Data
public class DatabaseQueryVO extends BasePageRequest {
    private String name;
}

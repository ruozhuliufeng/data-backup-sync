package tech.msop.data.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 通用查询条件
 */
@Getter
@Setter
public class BasePageRequest {
    /**
     * 当前页
     */
    private Integer start;
    /**
     * 每页数量
     */
    private Integer size;
}

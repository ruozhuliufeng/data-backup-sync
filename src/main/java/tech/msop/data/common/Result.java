package tech.msop.data.common;

import lombok.Data;

/**
 * Ajax 返回
 * @param <T> 数据
 */
@Data
public class Result<T> {
    private String msg;
    private Integer code;
    private T data;
}

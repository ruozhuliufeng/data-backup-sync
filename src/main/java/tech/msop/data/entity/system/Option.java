package tech.msop.data.entity.system;

import lombok.Data;

import java.util.List;

/**
 * 下拉选项
 *
 * @author ruozhuliufeng
 */
@Data
public class Option {
    /**
     * 选项ID
     */
    private Long id;
    /**
     * 选项编码
     */
    private String code;
    /**
     * 选项名称
     */
    private String name;
    /**
     * 父选项ID
     */
    private Long pid;
    /**
     * 子选项
     */
    private List<Option> children = null;
}

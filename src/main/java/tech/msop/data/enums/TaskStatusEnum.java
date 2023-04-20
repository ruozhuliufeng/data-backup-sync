package tech.msop.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务状态枚举
 *
 * @author ruozhuliufeng
 */
@Getter
@AllArgsConstructor
public enum TaskStatusEnum {
    NO_START(0,"未开始"),
    PROCEED(1,"进行中"),
    STOP(3,"异常终止"),
    ;
    final int status;
    final String msg;

}

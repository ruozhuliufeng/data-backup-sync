package tech.msop.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务类型枚举
 *
 * @author ruozhuliufeng
 */
@Getter
@AllArgsConstructor
public enum TaskTypeEnum {
    FILE_SYNC(0,"指定文件同步"),
    CATEGORY_SYNC(1,"指定文件夹同步"),
    DATABASE_SYNC(3,"数据库备份并同步"),
    DEFAULT_SYNC(999,""),
    ;
    final int type;
    final String msg;

    public static TaskTypeEnum of(Integer type){
        for (TaskTypeEnum typeEnum :values()){
            if (typeEnum.getType()==type){
                return typeEnum;
            }
        }
        return DATABASE_SYNC;
    }
}

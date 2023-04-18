package tech.msop.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 云端存储类别枚举
 *
 * @author ruozhuliufeng
 */
@Getter
@AllArgsConstructor
public enum StorageTypeEnum {
    ALI_OSS(0, "阿里云OSS"),
    BAIDU_BOS(1, "百度云BOS"),
    HUAWEI_OBS(2, "华为云OBS"),
    QINIU_KODO(3, "七牛云KODO"),
    TENCENT_COS(4, "腾讯云COS"),
    WEBDAV(5, "WEBDAV"),
    DEFAULT(999, ""),
    ;
    final int type;
    final String msg;

    public static StorageTypeEnum of(Integer type) {
        for (StorageTypeEnum typeEnum : values()) {
            if (typeEnum.getType() == type) {
                return typeEnum;
            }
        }
        return DEFAULT;
    }
}

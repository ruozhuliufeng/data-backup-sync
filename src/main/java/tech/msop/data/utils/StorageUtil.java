package tech.msop.data.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tech.msop.core.tool.utils.SpringUtil;
import tech.msop.data.entity.system.TaskEntity;
import tech.msop.data.enums.StorageTypeEnum;
import tech.msop.data.service.storage.*;

/**
 * 存储 工具类
 *
 * @author ruozhuliufeng
 */
@AllArgsConstructor
public class StorageUtil {
    private static WebDavStorageService webDavStorageService;
    private static AliOssStorageService aliOssStorageService;
    private static BaiduBosStorageService baiduBosStorageService;
    private static HuaweiObsStorageService huaweiObsStorageService;
    private static QiniuKodoStorageService qiniuKodoStorageService;
    private static TencentCosStorageService tencentCosStorageService;

    public static WebDavStorageService getWebDavStorageService() {
        if (webDavStorageService == null) {
            webDavStorageService = SpringUtil.getBean(WebDavStorageService.class);
        }
        return webDavStorageService;
    }

    public static AliOssStorageService getAliOssStorageService() {
        if (aliOssStorageService == null) {
            aliOssStorageService = SpringUtil.getBean(AliOssStorageService.class);
        }
        return aliOssStorageService;
    }

    public static BaiduBosStorageService getBaiduBosStorageService() {
        if (baiduBosStorageService == null) {
            baiduBosStorageService = SpringUtil.getBean(BaiduBosStorageService.class);
        }
        return baiduBosStorageService;
    }

    public static HuaweiObsStorageService getHuaweiObsStorageService() {
        if (huaweiObsStorageService == null) {
            huaweiObsStorageService = SpringUtil.getBean(HuaweiObsStorageService.class);
        }
        return huaweiObsStorageService;
    }

    public static QiniuKodoStorageService getQiniuKodoStorageService() {
        if (qiniuKodoStorageService == null) {
            qiniuKodoStorageService = SpringUtil.getBean(QiniuKodoStorageService.class);
        }
        return qiniuKodoStorageService;
    }

    public static TencentCosStorageService getTencentCosStorageService() {
        if (tencentCosStorageService == null) {
            tencentCosStorageService = SpringUtil.getBean(TencentCosStorageService.class);
        }
        return tencentCosStorageService;
    }

    /**
     * 根据任务信息获取存储标识
     *
     * @param taskEntity 任务信息
     * @return 云端存储标识
     */
    public static String getStorageName(TaskEntity taskEntity) {
        StorageTypeEnum typeEnum = StorageTypeEnum.of(taskEntity.getStorageType());
        String storageName;
        switch (typeEnum) {
            case WEBDAV:
                storageName = getWebDavStorageService().getById(taskEntity.getStorageId()).getPlatform();
            case ALI_OSS:
                storageName = getAliOssStorageService().getById(taskEntity.getStorageId()).getPlatform();
            case BAIDU_BOS:
                storageName = getBaiduBosStorageService().getById(taskEntity.getStorageId()).getPlatform();
            case HUAWEI_OBS:
                storageName = getHuaweiObsStorageService().getById(taskEntity.getStorageId()).getPlatform();
            case QINIU_KODO:
                storageName = getQiniuKodoStorageService().getById(taskEntity.getStorageId()).getPlatform();
            case TENCENT_COS:
                storageName = getTencentCosStorageService().getById(taskEntity.getStorageId()).getPlatform();
            default:
                storageName = "";
        }
        return storageName;
    }
}

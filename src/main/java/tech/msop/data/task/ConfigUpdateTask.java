package tech.msop.data.task;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.msop.core.file.storage.FileStorageService;
import tech.msop.core.file.storage.platform.*;
import tech.msop.core.tool.utils.BeanUtil;
import tech.msop.core.tool.utils.CollectionUtil;
import tech.msop.data.entity.storage.*;
import tech.msop.data.service.storage.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * 自动更新云存储配置信息
 *
 * @author ruozhuliufeng
 */
@Component
@AllArgsConstructor
public class ConfigUpdateTask implements ApplicationRunner {

    private final WebDavStorageService webDavStorageService;
    private final AliOssStorageService aliOssStorageService;
    private final BaiduBosStorageService baiduBosStorageService;
    private final HuaweiObsStorageService huaweiObsStorageService;
    private final QiniuKodoStorageService qiniuKodoStorageService;
    private final TencentCosStorageService tencentCosStorageService;
    private final FileStorageService fileStorageService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        storageHandle();
    }

    /**
     * 每分钟更新配置文件
     */
    @Scheduled(cron = "0 * * * * ?")
    public void scheduleTask() {
        storageHandle();
    }

    /**
     * 处理获取配置信息并处理
     */
    private void storageHandle() {
        //获得存储平台 List
        CopyOnWriteArrayList<FileStorage> list = fileStorageService.getFileStorageList();
        // 清除原有数据
        list.clear();
        List<WebDavStorageEntity> webDavStorageList = webDavStorageService.list();
        // 新增WebDAV存储平台
        if (CollectionUtil.isNotEmpty(webDavStorageList)) {
            List<WebDavFileStorage> webdavStorageList = webDavStorageList.stream().map(item -> {
                WebDavFileStorage storage = new WebDavFileStorage();
                BeanUtil.copy(item, storage);
                storage.setUser(item.getUsername());
                return storage;
            }).collect(Collectors.toList());
            list.addAll(webdavStorageList);
        }
        List<AliOssStorageEntity> aliOssStorageList = aliOssStorageService.list();
        // 新增阿里云OSS存储平台
        if (CollectionUtil.isNotEmpty(aliOssStorageList)) {
            List<AliyunOssFileStorage> ossStorageList = aliOssStorageList.stream().map(item -> {
                AliyunOssFileStorage oss = new AliyunOssFileStorage();
                BeanUtil.copy(item, oss);
                return oss;
            }).collect(Collectors.toList());
            list.addAll(ossStorageList);
        }
        List<BaiduBosStorageEntity> baiduBosStorageList = baiduBosStorageService.list();
        // 新增百度云BOS存储平台
        if (CollectionUtil.isNotEmpty(baiduBosStorageList)) {
            List<BaiduBosFileStorage> bosStorageList = baiduBosStorageList.stream().map(item -> {
                BaiduBosFileStorage bos = new BaiduBosFileStorage();
                BeanUtil.copy(item, bos);
                return bos;
            }).collect(Collectors.toList());
            list.addAll(bosStorageList);
        }
        List<HuaweiObsStorageEntity> huaweiObsStorageList = huaweiObsStorageService.list();
        // 新增华为云OBS存储平台
        if (CollectionUtil.isNotEmpty(huaweiObsStorageList)) {
            List<HuaweiObsFileStorage> obsStorageList = huaweiObsStorageList.stream().map(item -> {
                HuaweiObsFileStorage obs = new HuaweiObsFileStorage();
                BeanUtil.copy(item, obs);
                return obs;
            }).collect(Collectors.toList());
            list.addAll(obsStorageList);
        }
        List<QiniuKodoStorageEntity> qiniuKodoStorageList = qiniuKodoStorageService.list();
        // 新增七牛云KODO存储平台
        if (CollectionUtil.isNotEmpty(qiniuKodoStorageList)) {
            List<QiniuKodoFileStorage> kodoStorageList = qiniuKodoStorageList.stream().map(item -> {
                QiniuKodoFileStorage kodo = new QiniuKodoFileStorage();
                BeanUtil.copy(item, kodo);
                return kodo;
            }).collect(Collectors.toList());
            list.addAll(kodoStorageList);
        }
        List<TencentCosStorageEntity> tencentCosStorageList = tencentCosStorageService.list();
        // 新增腾讯云COS存储平台
        if (CollectionUtil.isNotEmpty(tencentCosStorageList)) {
            List<TencentCosFileStorage> cosStorageList = tencentCosStorageList.stream().map(item -> {
                TencentCosFileStorage cos = new TencentCosFileStorage();
                BeanUtil.copy(item, cos);
                return cos;
            }).collect(Collectors.toList());
            list.addAll(cosStorageList);
        }
    }
}

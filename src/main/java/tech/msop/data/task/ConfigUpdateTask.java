package tech.msop.data.task;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.msop.core.file.storage.properties.MsFileStorageProperties;
import tech.msop.core.tool.utils.BeanUtil;
import tech.msop.core.tool.utils.CollectionUtil;
import tech.msop.data.entity.storage.*;
import tech.msop.data.service.storage.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 更新云存储配置信息
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
    private final MsFileStorageProperties properties;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        storageHandle(properties);
    }

    /**
     * 每分钟更新配置文件
     */
    @Scheduled(cron = "0 * * * * ?")
    public void scheduleTask(){
        storageHandle(properties);
    }

    /**
     * 处理获取配置信息并处理
     * @param properties 配置信息
     */
    private void storageHandle(MsFileStorageProperties properties){
        List<WebDavStorageEntity> webDavStorageList = webDavStorageService.list();
        if (CollectionUtil.isNotEmpty(webDavStorageList)){
            List<MsFileStorageProperties.WebDAV> webDAVList = webDavStorageList.stream().map(item->{
                MsFileStorageProperties.WebDAV webDAV = new MsFileStorageProperties.WebDAV();
                BeanUtil.copy(item,webDAV);
                webDAV.setUser(item.getUsername());
                return webDAV;
            }).collect(Collectors.toList());
            properties.setWebDav(webDAVList);
        }
        List<AliOssStorageEntity> aliOssStorageList = aliOssStorageService.list();
        if (CollectionUtil.isNotEmpty(aliOssStorageList)){
            List<MsFileStorageProperties.AliyunOss> ossList = aliOssStorageList.stream().map(item->{
                MsFileStorageProperties.AliyunOss oss = new MsFileStorageProperties.AliyunOss();
                BeanUtil.copy(item,oss);
                return oss;
            }).collect(Collectors.toList());
            properties.setAliyunOss(ossList);
        }
        List<BaiduBosStorageEntity> baiduBosStorageList = baiduBosStorageService.list();
        if (CollectionUtil.isNotEmpty(baiduBosStorageList)){
            List<MsFileStorageProperties.BaiduBos> baiduBosList = baiduBosStorageList.stream().map(item->{
                MsFileStorageProperties.BaiduBos bos = new MsFileStorageProperties.BaiduBos();
                BeanUtil.copy(item,bos);
                return bos;
            }).collect(Collectors.toList());
            properties.setBaiduBos(baiduBosList);
        }
        List<HuaweiObsStorageEntity> huaweiObsStorageList = huaweiObsStorageService.list();
        if (CollectionUtil.isNotEmpty(huaweiObsStorageList)){
            List<MsFileStorageProperties.HuaweiObs> huaweiObsList = huaweiObsStorageList.stream().map(item->{
                MsFileStorageProperties.HuaweiObs obs = new MsFileStorageProperties.HuaweiObs();
                BeanUtil.copy(item,obs);
                return obs;
            }).collect(Collectors.toList());
            properties.setHuaweiObs(huaweiObsList);
        }
        List<QiniuKodoStorageEntity> qiniuKodoStorageList = qiniuKodoStorageService.list();
        if (CollectionUtil.isNotEmpty(qiniuKodoStorageList)){
            List<MsFileStorageProperties.QiniuKodo> qiniuKodoList = qiniuKodoStorageList.stream().map(item->{
                MsFileStorageProperties.QiniuKodo kodo = new MsFileStorageProperties.QiniuKodo();
                BeanUtil.copy(item,kodo);
                return kodo;
            }).collect(Collectors.toList());
            properties.setQiniuKodo(qiniuKodoList);
        }
        List<TencentCosStorageEntity> tencentCosStorageList = tencentCosStorageService.list();
        if (CollectionUtil.isNotEmpty(tencentCosStorageList)){
            List<MsFileStorageProperties.TencentCos> tencentCosList = tencentCosStorageList.stream().map(item->{
                MsFileStorageProperties.TencentCos cos = new MsFileStorageProperties.TencentCos();
                BeanUtil.copy(item,cos);
                return cos;
            }).collect(Collectors.toList());
            properties.setTencentCos(tencentCosList);
        }
    }
}

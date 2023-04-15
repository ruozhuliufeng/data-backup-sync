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
import tech.msop.data.entity.storage.WebDavStorageEntity;
import tech.msop.data.service.storage.WebDavStorageService;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 更新云存储配置信息
 */
@Component
@AllArgsConstructor
public class ConfigUpdateTask implements ApplicationRunner {

    private final WebDavStorageService webDavStorageService;
    private final MsFileStorageProperties properties;
    @Override
    public void run(ApplicationArguments args) throws Exception {
//        storageHandle(properties);
    }

//    @Scheduled(cron = "0 * * * * ?")
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
                return webDAV;
            }).collect(Collectors.toList());
            properties.setWebDav(webDAVList);
        }
    }
}

package tech.msop.data.service.storage.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.msop.data.entity.storage.WebDavStorageEntity;
import tech.msop.data.mapper.WebDavStorageMapper;
import tech.msop.data.service.storage.WebDavStorageService;

/**
 * WebDav 存储服务
 */
@Service
public class WebDavStorageServiceImpl extends ServiceImpl<WebDavStorageMapper, WebDavStorageEntity>
        implements WebDavStorageService {
}

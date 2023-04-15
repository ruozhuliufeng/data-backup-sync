package tech.msop.data.service.storage.impl;

import org.springframework.stereotype.Service;
import tech.msop.data.entity.storage.WebDavStorageEntity;
import tech.msop.data.mapper.WebDavStorageMapper;
import tech.msop.data.service.storage.WebDavStorageService;
import tech.msop.mybatis.service.impl.SuperServiceImpl;

/**
 * WebDav 存储服务
 */
@Service
public class WebDavStorageServiceImpl extends SuperServiceImpl<WebDavStorageMapper, WebDavStorageEntity>
        implements WebDavStorageService {
}

package tech.msop.data.service.storage.impl;

import org.springframework.stereotype.Service;
import tech.msop.data.entity.storage.AliOssStorageEntity;
import tech.msop.data.mapper.AliOssStorageMapper;
import tech.msop.data.service.storage.AliOssStorageService;
import tech.msop.mybatis.service.impl.SuperServiceImpl;

/**
 * AliOss 存储服务
 */
@Service
public class AliOssStorageServiceImpl extends SuperServiceImpl<AliOssStorageMapper, AliOssStorageEntity>
        implements AliOssStorageService {
}

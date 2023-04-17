package tech.msop.data.service.storage.impl;

import org.springframework.stereotype.Service;
import tech.msop.data.entity.storage.TencentCosStorageEntity;
import tech.msop.data.mapper.TencentCosStorageMapper;
import tech.msop.data.service.storage.TencentCosStorageService;
import tech.msop.mybatis.service.impl.SuperServiceImpl;

/**
 * 腾讯云 COS 存储服务
 */
@Service
public class TencentCosStorageServiceImpl extends SuperServiceImpl<TencentCosStorageMapper, TencentCosStorageEntity>
        implements TencentCosStorageService {
}

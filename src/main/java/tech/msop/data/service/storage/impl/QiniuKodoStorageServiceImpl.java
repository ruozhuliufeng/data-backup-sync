package tech.msop.data.service.storage.impl;

import org.springframework.stereotype.Service;
import tech.msop.data.entity.storage.QiniuKodoStorageEntity;
import tech.msop.data.mapper.QiniuKodoStorageMapper;
import tech.msop.data.service.storage.QiniuKodoStorageService;
import tech.msop.mybatis.service.impl.SuperServiceImpl;

/**
 * 七牛云 Kodo 存储服务
 */
@Service
public class QiniuKodoStorageServiceImpl extends SuperServiceImpl<QiniuKodoStorageMapper, QiniuKodoStorageEntity>
        implements QiniuKodoStorageService {
}

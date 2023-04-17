package tech.msop.data.service.storage.impl;

import org.springframework.stereotype.Service;
import tech.msop.data.entity.storage.BaiduBosStorageEntity;
import tech.msop.data.mapper.BaiduBosStorageMapper;
import tech.msop.data.service.storage.BaiduBosStorageService;
import tech.msop.mybatis.service.impl.SuperServiceImpl;

/**
 * BaiduBos 存储服务
 */
@Service
public class BaiduBosStorageServiceImpl extends SuperServiceImpl<BaiduBosStorageMapper, BaiduBosStorageEntity>
        implements BaiduBosStorageService {
}

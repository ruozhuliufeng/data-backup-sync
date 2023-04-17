package tech.msop.data.service.storage.impl;

import org.springframework.stereotype.Service;
import tech.msop.data.entity.storage.HuaweiObsStorageEntity;
import tech.msop.data.mapper.HuaweiObsStorageMapper;
import tech.msop.data.service.storage.HuaweiObsStorageService;
import tech.msop.mybatis.service.impl.SuperServiceImpl;

/**
 * HuaweiObs 存储服务
 */
@Service
public class HuaweiObsStorageServiceImpl extends SuperServiceImpl<HuaweiObsStorageMapper, HuaweiObsStorageEntity>
        implements HuaweiObsStorageService {
}

package tech.msop.data.service.system.impl;

import org.springframework.stereotype.Service;
import tech.msop.data.entity.system.DatabaseEntity;
import tech.msop.data.mapper.DatabaseMapper;
import tech.msop.data.service.system.DatabaseService;
import tech.msop.mybatis.service.impl.SuperServiceImpl;

@Service
public class DatabaseServiceImpl extends SuperServiceImpl<DatabaseMapper, DatabaseEntity>
        implements DatabaseService {
}

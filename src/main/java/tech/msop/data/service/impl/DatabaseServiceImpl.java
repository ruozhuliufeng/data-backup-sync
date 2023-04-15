package tech.msop.data.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.msop.data.entity.system.DatabaseEntity;
import tech.msop.data.service.DatabaseService;

/**
 * 数据库信息处理
 *
 * @author ruozhuliufeng
 */
@Service
@AllArgsConstructor
public class DatabaseServiceImpl implements DatabaseService {

    @Override
    public void saveDatabase(DatabaseEntity database) {

    }
}

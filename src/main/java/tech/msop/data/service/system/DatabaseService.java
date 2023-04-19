package tech.msop.data.service.system;

import tech.msop.data.entity.system.DatabaseEntity;
import tech.msop.data.entity.system.Option;
import tech.msop.mybatis.service.ISuperService;

import java.util.List;

public interface DatabaseService extends ISuperService<DatabaseEntity> {
    /**
     * 查询数据库信息选项
     *
     * @return 数据库信息
     */
    List<Option> queryDatabaseOptionList();
}

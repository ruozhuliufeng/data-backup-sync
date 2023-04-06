package tech.msop.data.service;

import tech.msop.data.entity.system.DatabaseEntity;

public interface DatabaseService{
    /**
     * 保存数据库信息
     * @param database 数据库信息
     */
    void saveDatabase(DatabaseEntity database);
}

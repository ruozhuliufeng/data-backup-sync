package tech.msop.data.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * 数据库信息枚举
 */
@Getter
@AllArgsConstructor
public enum DatabaseEnum {

    /**
     * MySQL 数据库
     */
    MySQL(0, "MySQL", "com.mysql.cj.jdbc.Driver", "jdbc:mysql://%s:%s/%s?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai"),
    /**
     * Oracle 数据库
     */

    Oracle(1, "Oracle", "com.cj.Driver", "jdbc://"),
    ;
    /**
     * 数据库类型 0：MySQL  1：Oracle 2：SqlServer 3：Postgres
     */
    final int type;
    /**
     * 数据库类型名称
     */
    final String name;
    /**
     * Driver类地址
     */
    final String driver;
    /**
     * jdbc 连接
     */
    final String jdbc;

    /**
     * 根据类型获取数据库执行类等信息
     *
     * @param type 类型
     * @return 数据库信息
     */
    public DatabaseEnum of(Integer type) {
        for (DatabaseEnum data : values()) {
            if (data.getType() == type) {
                return data;
            }
        }
        return null;
    }

}

package tech.msop.data.constants;

/**
 * 常量
 */
public interface DataBackupConstant {
    /**
     * 数据库地址
     */
    String DB_URL = "DB_URL";
    /**
     * 连接类
     */
    String DB_DRIVER_MYSQL_CLASS= "com.mysql.cj.jdbc.Driver";
    /**
     * 连接用户
     */
    String DB_USER = "DB_USER";
    /**
     * 连接密码
     */
    String DB_PASSWORD = "DB_PASSWORD";
    /**
     * 数据库
     */
    String DB_MYSQL = "mysql";
    /**
     * 默认备份文件目录
     */
    String DEFAULT_BACKUP_DIR = "/data/backup";
}

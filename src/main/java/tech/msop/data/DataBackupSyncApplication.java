package tech.msop.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.ObjectUtils;
import tech.msop.data.constants.DataBackupConstant;
import tech.msop.data.enums.DatabaseEnum;

import java.util.Properties;

@EnableScheduling
@SpringBootApplication
public class DataBackupSyncApplication {

    public static void main(String[] args) {
        // 获取Docker 中环境变量，使用用户定义的数据库
        String dbHost = System.getenv(DataBackupConstant.DB_HOST);
        String dbName = System.getenv(DataBackupConstant.DB_NAME);
        String dbPort = System.getenv(DataBackupConstant.DB_PORT);
        String dbUser = System.getenv(DataBackupConstant.DB_USER);
        String dbPassword = System.getenv(DataBackupConstant.DB_PASSWORD);
        if (!ObjectUtils.isEmpty(dbHost)
                && !ObjectUtils.isEmpty(dbName)
                && !ObjectUtils.isEmpty(dbPort)
                && !ObjectUtils.isEmpty(dbUser)
                && !ObjectUtils.isEmpty(dbPassword)) {
            // 暂时只支持MySQL数据库
            Properties props = System.getProperties();
            String dbUrl = String.format(DatabaseEnum.MySQL.getJdbc(),dbHost,dbPort,dbName);
            props.setProperty("spring.datasource.url",dbUrl);
            props.setProperty("spring.datasource.driver-class-name",DataBackupConstant.DB_DRIVER_MYSQL_CLASS);
            props.setProperty("spring.datasource.username",dbUser);
            props.setProperty("spring.datasource.password",dbPassword);
            // JPA 更新
            props.setProperty("spring.jpa.database",DataBackupConstant.DB_MYSQL);
            props.remove("spring.h2");
        }
        SpringApplication.run(DataBackupSyncApplication.class, args);
    }

}

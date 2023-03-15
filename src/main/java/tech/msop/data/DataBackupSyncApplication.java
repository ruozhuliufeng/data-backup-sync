package tech.msop.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ObjectUtils;
import tech.msop.data.constants.DataBackupConstant;

import java.util.Properties;

@SpringBootApplication
public class DataBackupSyncApplication {

    public static void main(String[] args) {
        // 获取Docker 中环境变量，使用用户定义的数据库
        String dbUrl = System.getenv(DataBackupConstant.DB_URL);
        String dbUser = System.getenv(DataBackupConstant.DB_USER);
        String dbPassword = System.getenv(DataBackupConstant.DB_PASSWORD);
        if (!ObjectUtils.isEmpty(dbUrl)
                && !ObjectUtils.isEmpty(dbUser)
                && !ObjectUtils.isEmpty(dbPassword)) {
            // 暂时只支持MySQL数据库
            Properties props = System.getProperties();
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

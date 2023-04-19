package tech.msop.data.task;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.msop.core.file.storage.FileStorageService;
import tech.msop.core.tool.constant.StringConstant;
import tech.msop.core.tool.utils.DateUtil;
import tech.msop.data.constants.DataBackupConstant;
import tech.msop.data.entity.system.DatabaseEntity;
import tech.msop.data.entity.system.TaskEntity;
import tech.msop.data.enums.DatabaseEnum;
import tech.msop.data.service.system.TaskService;
import tech.msop.data.utils.DatabaseUtil;
import tech.msop.data.utils.StorageUtil;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 数据备份与同步任务
 *
 * @author ruozhuliufeng
 */
@Component
@AllArgsConstructor
@Slf4j
public class DataBackupTask {

    private final FileStorageService fileStorageService;
    private final TaskService taskService;

//    @Scheduled(cron = "0 * * * * ?")
    public void scheduleTask(){
        List<TaskEntity> taskList = taskService.list();
        taskList.forEach(item->{
            DatabaseEntity database = DatabaseUtil.getDatabaseByTask(item);
            if (DatabaseEnum.MySQL.getType()==database.getType()){
                String databaseFile = exportMySQLSql(database,item);
                log.info("生成的数据库文件："+databaseFile);
                String platform = StorageUtil.getStorageName(item);
                assert databaseFile != null;
                File file = new File(databaseFile);
                fileStorageService.of(file)
                        .setName(file.getName())
                        .setPlatform(platform)
                        .upload();
            }
        });
    }

    /**
     * 备份MySQL数据库并返回相关信息
     */
    @SneakyThrows
    private String exportMySQLSql(DatabaseEntity database, TaskEntity task) {
        String host = database.getHost();
        String dataBaseName = task.getDbName();
        String time = DateUtil.formatDate(new Date());
        String fileName = "MySQL-" + time + "-" + dataBaseName + ".sql";
        String filePath = DataBackupConstant.DEFAULT_BACKUP_DIR + StringConstant.SLASH;
        // 指定导出的 sql 存放的文件夹
        File saveFile = new File(filePath);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }
        StringBuilder sb = new StringBuilder();
        // 拼接备份命令
        sb.append("mysqldump").append(" --opt")
                .append(" -h ").append(host).append(" --user=")
                .append(database.getUsername())
                .append(" --password=")
                .append(database.getPassword())
                .append(" --result-file=").append(filePath).append(fileName)
                .append(" --default-character-set=utf8mb4 ")
                .append(dataBaseName);
        try {
            Process exec = Runtime.getRuntime().exec(sb.toString());
            if (exec.waitFor() == 0) {
                log.info("数据库备份成功，保存路径：" + DataBackupConstant.DEFAULT_BACKUP_DIR);
            } else {
                log.info("process.waitFor:{}", exec.waitFor());
                return null;
            }
        } catch (IOException e) {
            log.error("备份 数据库 出现 IO异常 ", e);
            return null;
        } catch (InterruptedException e) {
            log.error("备份 数据库 出现 线程中断异常 ", e);
            return null;
        } catch (Exception e) {
            log.error("备份 数据库 出现 其他异常 ", e);
            return null;
        }
        return filePath + fileName;
    }
}

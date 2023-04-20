package tech.msop.data.service.task.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import tech.msop.core.file.storage.FileStorageService;
import tech.msop.core.tool.constant.StringConstant;
import tech.msop.core.tool.utils.DateUtil;
import tech.msop.data.constants.DataBackupConstant;
import tech.msop.data.entity.system.DatabaseEntity;
import tech.msop.data.entity.system.TaskEntity;
import tech.msop.data.enums.DatabaseEnum;
import tech.msop.data.enums.TaskTypeEnum;
import tech.msop.data.service.system.TaskService;
import tech.msop.data.service.task.AbstractSchedulerTask;
import tech.msop.data.utils.DatabaseUtil;
import tech.msop.data.utils.StorageUtil;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 数据库同步
 *
 * @author ruozhuliufeng
 */
@Service
@Slf4j
@AllArgsConstructor
public class DatabaseSyncScheduler extends AbstractSchedulerTask {
    private final TaskService taskService;
    private final FileStorageService fileStorageService;

    /**
     * 获取数据库中的任务列表
     *
     * @return 任务列表
     */
    @Override
    protected List<TaskEntity> getTaskList() {
        // 获取任务为同步数据库的所有已启用的任务
        return taskService.list(Wrappers.<TaskEntity>query().lambda().eq(TaskEntity::getTaskType, TaskTypeEnum.DATABASE_SYNC.getType())
                .eq(TaskEntity::getStatus, 1));
    }

    /**
     * 执行任务
     *
     * @param task 任务详情
     */
    @Override
    protected void execute(TaskEntity task) {
        DatabaseEntity database = DatabaseUtil.getDatabaseByTask(task);
        // 备份MySQL数据库并上传
        if (DatabaseEnum.MySQL.getType() == database.getType()) {
            String databaseFile = exportMySQLSql(database, task);
            log.info("生成的数据库文件：" + databaseFile);
            String platform = StorageUtil.getStorageName(task);
            assert databaseFile != null;
            File file = new File(databaseFile);
            fileStorageService.of(file)
                    .setName(file.getName())
                    .setPlatform(platform)
                    .upload();
        }
    }

    /**
     * 获取默认的表达式，当配置的CRON表达式异常时，执行默认的表达式
     *
     * @return CRON表达式
     */
    @Override
    protected String getDefaultCron() {
        return DataBackupConstant.DEFAULT_CRON;
    }

    /**
     * 更新任务状态
     *
     * @param task 状态
     */
    @Override
    protected void updateTaskStatus(TaskEntity task) {
        taskService.updateById(task);
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

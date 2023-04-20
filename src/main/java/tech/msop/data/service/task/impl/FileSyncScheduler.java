package tech.msop.data.service.task.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tech.msop.core.file.storage.FileStorageService;
import tech.msop.core.tool.constant.StringConstant;
import tech.msop.core.tool.utils.StringUtil;
import tech.msop.data.constants.DataBackupConstant;
import tech.msop.data.entity.system.TaskEntity;
import tech.msop.data.enums.TaskTypeEnum;
import tech.msop.data.service.system.TaskService;
import tech.msop.data.service.task.AbstractSchedulerTask;
import tech.msop.data.utils.StorageUtil;

import java.io.File;
import java.util.List;

/**
 * 文件同步
 *
 * @author ruozhuliufeng
 */
@Service
@AllArgsConstructor
public class FileSyncScheduler extends AbstractSchedulerTask {
    private final TaskService taskService;
    private final FileStorageService fileStorageService;

    /**
     * 获取数据库中的任务列表
     *
     * @return 任务列表
     */
    @Override
    protected List<TaskEntity> getTaskList() {
        // 获取任务为同步文件的所有已启用的任务
        return taskService.list(Wrappers.<TaskEntity>query().lambda().eq(TaskEntity::getTaskType, TaskTypeEnum.FILE_SYNC.getType())
                .eq(TaskEntity::getStatus, 1));
    }

    /**
     * 执行任务
     *
     * @param task 任务详情
     */
    @Override
    protected void execute(TaskEntity task) {
        String message = "";
        if (ObjectUtils.isEmpty(task.getSyncPath())) {
            message = "同步文件地址为空";
        }
        if (!ObjectUtils.isEmpty(task.getSyncPath())
                &&
                (task.getSyncPath().endsWith(StringConstant.SLASH)
                        || task.getSyncPath().startsWith(StringConstant.SLASH)
                )
        ) {
            message = "同步文件地址格式有误";
        }
        String path = DataBackupConstant.DEFAULT_BACKUP_DIR + task.getSyncPath();
        File file = new File(path);
        if (!file.exists()) {
            message = "同步文件不存在";
        }
        // 错误信息不为空，不处理其他操作
        if (StringUtil.isNotBlank(message)) {
            task.setErrorMessage(message);
            updateTaskStatus(task);
            return;
        }
        // 获取上传的云端存储标识
        String platform = StorageUtil.getStorageName(task);
        // 文件上传
        fileStorageService.of(file)
                .setName(file.getName())
                .setPlatform(platform)
                .upload();
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
}

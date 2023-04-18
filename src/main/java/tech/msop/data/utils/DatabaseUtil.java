package tech.msop.data.utils;

import lombok.AllArgsConstructor;
import tech.msop.core.tool.utils.SpringUtil;
import tech.msop.data.entity.system.TaskEntity;
import tech.msop.data.service.system.DatabaseService;

/**
 * 数据库工具类
 *
 * @author ruozhuliufeng
 */
@AllArgsConstructor
public class DatabaseUtil {
    private static DatabaseService databaseService;

    public static DatabaseService getDatabaseService() {
        if (databaseService == null) {
            databaseService = SpringUtil.getBean(DatabaseService.class);
        }
        return databaseService;
    }

    /**
     * 根据任务信息获取数据库标识
     *
     * @param entity 数据库ID
     * @return 数据库标识
     */
    public static String getDatabaseNameByTask(TaskEntity entity) {
        return getDatabaseService().getById(entity.getDatabaseId()).getDatabaseName();
    }
}

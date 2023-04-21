package tech.msop.data.task;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import tech.msop.data.service.task.impl.CategorySyncScheduler;
import tech.msop.data.service.task.impl.DatabaseSyncScheduler;
import tech.msop.data.service.task.impl.FileSyncScheduler;

import javax.annotation.Resource;

@Component
@Order(1)
public class SchedulerTaskRunner implements CommandLineRunner {
    @Resource
    private FileSyncScheduler fileSyncScheduler;
    @Resource
    private DatabaseSyncScheduler databaseSyncScheduler;
    @Resource
    private CategorySyncScheduler categorySyncScheduler;
    @Override
    public void run(String... args) throws Exception {
        fileSyncScheduler.executeTask();
        databaseSyncScheduler.executeTask();
        categorySyncScheduler.executeTask();
    }
}

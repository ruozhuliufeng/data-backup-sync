package tech.msop.data.service.system.impl;

import org.springframework.stereotype.Service;
import tech.msop.data.entity.system.TaskEntity;
import tech.msop.data.mapper.TaskMapper;
import tech.msop.data.service.system.TaskService;
import tech.msop.mybatis.service.impl.SuperServiceImpl;

@Service
public class TaskServiceImpl extends SuperServiceImpl<TaskMapper, TaskEntity>
        implements TaskService {
}

package tech.msop.data.service.system.impl;

import org.springframework.stereotype.Service;
import tech.msop.data.entity.system.Option;
import tech.msop.data.entity.system.TaskEntity;
import tech.msop.data.enums.StorageTypeEnum;
import tech.msop.data.mapper.TaskMapper;
import tech.msop.data.service.system.TaskService;
import tech.msop.mybatis.service.impl.SuperServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl extends SuperServiceImpl<TaskMapper, TaskEntity>
        implements TaskService {
    @Override
    public List<Option> queryStorageOptionList() {
        // 新增父级选项
        List<Option> parentOptionList = new ArrayList<>();
        for (StorageTypeEnum typeEnum:StorageTypeEnum.values()){
            if (typeEnum.getType()!=StorageTypeEnum.DEFAULT.getType()){
                Option option = new Option();
                option.setPid(0L);
                option.setId((long) typeEnum.getType());
                option.setName(typeEnum.getMsg());
                parentOptionList.add(option);
            }
        }
        List<Option> list =  baseMapper.queryStorageOptionList();
        list.addAll(parentOptionList);
        return list;
    }
}

package tech.msop.data.service.system.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import tech.msop.data.entity.system.DatabaseEntity;
import tech.msop.data.entity.system.Option;
import tech.msop.data.mapper.DatabaseMapper;
import tech.msop.data.service.system.DatabaseService;
import tech.msop.mybatis.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DatabaseServiceImpl extends SuperServiceImpl<DatabaseMapper, DatabaseEntity>
        implements DatabaseService {
    /**
     * 查询数据库信息选项
     *
     * @return 数据库信息
     */
    @Override
    public List<Option> queryDatabaseOptionList() {
        List<DatabaseEntity> list = list(Wrappers.<DatabaseEntity>query().lambda().eq(DatabaseEntity::getStatus,1));
        return list.stream().map(item->{
            Option option = new Option();
            option.setId(item.getId());
            option.setName(item.getDatabaseName());
            return option;
        }).collect(Collectors.toList());
    }
}

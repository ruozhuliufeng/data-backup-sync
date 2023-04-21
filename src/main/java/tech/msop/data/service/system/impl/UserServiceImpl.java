package tech.msop.data.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import tech.msop.data.entity.system.UserEntity;
import tech.msop.data.mapper.UserMapper;
import tech.msop.data.service.system.UserService;
import tech.msop.mybatis.service.impl.SuperServiceImpl;

/**
 * 用户服务
 */
@Service
public class UserServiceImpl extends SuperServiceImpl<UserMapper, UserEntity>
        implements UserService {
    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    @Override
    public UserEntity getByUsername(String username) {
        return getOne(new QueryWrapper<UserEntity>().eq("username",username));
    }
}

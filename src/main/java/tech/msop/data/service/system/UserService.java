package tech.msop.data.service.system;

import tech.msop.data.entity.system.UserEntity;
import tech.msop.mybatis.service.ISuperService;

/**
 * 用户服务
 *
 * @author ruozhuliufeng
 */
public interface UserService extends ISuperService<UserEntity> {
    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserEntity getByUsername(String username);
}

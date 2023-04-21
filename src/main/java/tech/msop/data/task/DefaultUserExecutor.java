package tech.msop.data.task;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tech.msop.data.constants.DataBackupConstant;
import tech.msop.data.entity.system.UserEntity;
import tech.msop.data.service.system.UserService;

/**
 * 判断是否存在用户，若不存在，新增默认用户
 */
@Component
@Order(1)
@Slf4j
@AllArgsConstructor
public class DefaultUserExecutor implements ApplicationRunner {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserEntity user = userService.getByUsername(DataBackupConstant.DEFAULT_USER_PASSWORD);
        if (ObjectUtil.isEmpty(user)){
            user = new UserEntity();
            user.setUsername(DataBackupConstant.DEFAULT_USER_PASSWORD);
            user.setNickname(DataBackupConstant.DEFAULT_USER_PASSWORD);
            user.setPassword(passwordEncoder.encode(DataBackupConstant.DEFAULT_USER_PASSWORD));
            userService.save(user);
        }
    }
}

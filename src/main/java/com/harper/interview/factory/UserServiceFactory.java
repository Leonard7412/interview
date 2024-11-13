package com.harper.interview.factory;

import com.harper.interview.service.UserService;
import com.harper.interview.service.impl.UserServiceBySessionImpl;
import com.harper.interview.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.interview.factory
 * @Author: liuhb_mios_ah
 * @CreateTime: 2024-05-16  10:39
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class UserServiceFactory {

    private final UserService userServiceImpl;
    private final UserService userServiceBySessionImpl;

    @Autowired
    public UserServiceFactory(UserServiceImpl userServiceImpl, UserServiceBySessionImpl userServiceBySessionImpl) {
        this.userServiceImpl = userServiceImpl;
        this.userServiceBySessionImpl = userServiceBySessionImpl;
    }

    public UserService getUserService(boolean useRedis) {
        return useRedis ? userServiceImpl : userServiceBySessionImpl;
    }
}

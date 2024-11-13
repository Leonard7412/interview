package com.harper.template.factory;

import com.harper.template.service.UserService;
import com.harper.template.service.impl.UserServiceBySessionImpl;
import com.harper.template.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.template.factory
 * @Author: liuhb_mios_ah
 * @CreateTime: 2024-05-16  10:39
 * @Description: TODO
 * @Version: 1.0
 */
@Service
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

package com.harper.template.controller;

import com.harper.template.common.BaseResult;
import com.harper.template.common.BusiException;
import com.harper.template.common.ErrorCode;
import com.harper.template.common.Result;
import com.harper.template.dto.user.*;
import com.harper.template.entity.User;
import com.harper.template.factory.UserServiceFactory;
import com.harper.template.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.harper.template.utils.Consts.TRUE;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.template.controller
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-11-25  15:41
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Value("${spring.redis.enable}")
    private String redisSwitch;

    @Resource
    private UserServiceFactory userServiceFactory;

    /**
     * @description: 账号密码登录
     * @author: liuhb_mios_ah
     * @date: 2023/11/30 15:13
     * @param: [loginInDTO, httpSession]
     * @return: com.harper.template.common.BaseResult<com.harper.template.dto.user.LoginOutDTO>
     **/
    @PostMapping("/login")
    private BaseResult<LoginOutDTO> login(@RequestBody LoginInDTO loginInDTO, HttpServletRequest request) {
        if (loginInDTO == null) {
            throw new BusiException(ErrorCode.PARAM_ERROR);
        }
        boolean useRedis = TRUE.equals(redisSwitch);
        UserService userServiceToUse = userServiceFactory.getUserService(useRedis);
        LoginOutDTO loginOutDTO = userServiceToUse.login(loginInDTO, request);

        return Result.ok(loginOutDTO);
    }

    /**
     * @description: 短信验证码发送
     * @author: liuhb_mios_ah
     * @date: 2023/11/30 16:30
     * @param: [sendCodeInDTO]
     * @return: com.harper.template.common.BaseResult<com.harper.template.dto.user.SendCodeOutDTO>
     **/
    @PostMapping("/send/code")
    private BaseResult<SendCodeOutDTO> send(@RequestBody SendCodeInDTO sendCodeInDTO, HttpServletRequest request) {
        boolean useRedis = TRUE.equals(redisSwitch);
        UserService userServiceToUse = userServiceFactory.getUserService(useRedis);
        SendCodeOutDTO out = userServiceToUse.send(sendCodeInDTO, request);
        return Result.ok(out);
    }

    /**
     * @description: 手机号登录
     * @author: liuhb_mios_ah
     * @date: 2023/12/4 14:11
     * @param: [loginByPhoneInDTO]
     * @return: com.harper.template.common.BaseResult<com.harper.template.dto.user.LoginOutDTO>
     **/
    @PostMapping("/phone/login")
    private BaseResult<LoginOutDTO> loginByPhone(@RequestBody LoginByPhoneInDTO loginByPhoneInDTO, HttpServletRequest request) {
        if (loginByPhoneInDTO == null) {
            throw new BusiException(ErrorCode.PARAM_ERROR);
        }
        boolean useRedis = TRUE.equals(redisSwitch);
        UserService userServiceToUse = userServiceFactory.getUserService(useRedis);
        LoginOutDTO loginOutDTO = userServiceToUse.loginByPhone(loginByPhoneInDTO, request);
        return Result.ok(loginOutDTO);
    }

    /**
     * @description: 用户注册
     * @author: liuhb_mios_ah
     * @date: 2023/12/4 16:07
     * @param: [registerInDTO]
     * @return: com.harper.template.common.BaseResult<java.lang.String>
     **/
    @PostMapping("/register")
    private BaseResult<String> register(@RequestBody RegisterInDTO registerInDTO, HttpServletRequest request) {
        if (registerInDTO == null) {
            throw new BusiException(ErrorCode.PARAM_ERROR);
        }
        boolean useRedis = TRUE.equals(redisSwitch);
        UserService userServiceToUse = userServiceFactory.getUserService(useRedis);
        String result = userServiceToUse.register(registerInDTO, request);
        return Result.ok(result);
    }

    @PostMapping("/qryUserInfo")
    private BaseResult<User> qryUserInfo(@RequestBody QryUserInDTO qryUserInDTO) {
        if (qryUserInDTO == null) {
            throw new BusiException(ErrorCode.PARAM_ERROR);
        }
        boolean useRedis = TRUE.equals(redisSwitch);
        UserService userServiceToUse = userServiceFactory.getUserService(useRedis);
        User user = userServiceToUse.getById(qryUserInDTO.getId());
        return Result.ok(user);
    }
}

package com.harper.interview.aop;

import com.harper.interview.annotation.AuthCheck;
import com.harper.interview.common.BusiException;
import com.harper.interview.common.ErrorCode;

import com.harper.interview.entity.User;
import com.harper.interview.enums.UserRoleEnum;
import com.harper.interview.factory.UserServiceFactory;
import com.harper.interview.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.harper.interview.utils.Consts.TRUE;

/**
 * 权限校验 AOP
 *
 */
/*@Aspect
@Component*/
public class AuthInterceptor {/*
    @Value("${switch.now}")
    private String redisSwitch;

    @Resource
    private UserServiceFactory userServiceFactory;

    *//**
     * 执行拦截
     *
     * @param joinPoint
     * @param authCheck
     * @return
     *//*
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        boolean useRedis = TRUE.equals(redisSwitch);
        UserService userServiceToUse = userServiceFactory.getUserService(useRedis);
        // 当前登录用户
        User loginUser = userServiceToUse.getLoginUser(request);
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        // 不需要权限，放行
        if (mustRoleEnum == null) {
            return joinPoint.proceed();
        }
        // 必须有该权限才通过
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());
        if (userRoleEnum == null) {
            throw new BusiException(ErrorCode.NO_AUTH_ERROR);
        }
        // 如果被封号，直接拒绝
        if (UserRoleEnum.BAN.equals(userRoleEnum)) {
            throw new BusiException(ErrorCode.NO_AUTH_ERROR);
        }
        // 必须有管理员权限
        if (UserRoleEnum.ADMIN.equals(mustRoleEnum)) {
            // 用户没有管理员权限，拒绝
            if (!UserRoleEnum.ADMIN.equals(userRoleEnum)) {
                throw new BusiException(ErrorCode.NO_AUTH_ERROR);
            }
        }
        // 通过权限校验，放行
        return joinPoint.proceed();
    }
*/}


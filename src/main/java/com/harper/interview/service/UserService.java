package com.harper.interview.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harper.interview.dto.user.*;
import com.harper.interview.entity.User;

import javax.servlet.http.HttpServletRequest;


public interface UserService extends IService<User> {
    LoginOutDTO login(LoginInDTO loginInDTO, HttpServletRequest request);

    SendCodeOutDTO send(SendCodeInDTO sendCodeInDTO, HttpServletRequest request);

    LoginOutDTO loginByPhone(LoginByPhoneInDTO loginInDTO, HttpServletRequest request);

    String register(RegisterInDTO registerInDTO, HttpServletRequest request);

    User getLoginUser(HttpServletRequest request);
}

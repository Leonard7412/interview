package com.harper.template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.harper.template.dto.user.*;
import com.harper.template.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends IService<User> {
    LoginOutDTO login(LoginInDTO loginInDTO, HttpServletRequest request);

    SendCodeOutDTO send(SendCodeInDTO sendCodeInDTO, HttpServletRequest request);

    LoginOutDTO loginByPhone(LoginByPhoneInDTO loginInDTO, HttpServletRequest request);

    String register(RegisterInDTO registerInDTO, HttpServletRequest request);
}

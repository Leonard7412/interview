package com.harper.interview.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSONObject;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.harper.interview.common.BusiException;
import com.harper.interview.common.ErrorCode;
import com.harper.interview.config.AliyunSmsClientFactory;
import com.harper.interview.dto.user.*;
import com.harper.interview.entity.User;
import com.harper.interview.mapper.UserMapper;
import com.harper.interview.service.UserService;
import com.harper.interview.utils.PatternUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.harper.interview.utils.Consts.*;
import static com.harper.interview.utils.SequenceUtils.generateUniqueId;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.interview.service.impl
 * @Author: liuhb_mios_ah
 * @CreateTime: 2024-05-16  10:23
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@Slf4j
public class UserServiceBySessionImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private AliyunSmsClientFactory aliyunSmsClientFactory;

    @Override
    public LoginOutDTO login(LoginInDTO loginInDTO, HttpServletRequest request) {
        String account = loginInDTO.getAccount();
        String loginPassword = loginInDTO.getPassword();
        if (StringUtils.isAnyBlank(account, loginPassword)) {
            throw new BusiException(ErrorCode.PARAM_ERROR, "参数为空");
        }
        // 1.获取账号比对数据库获取用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", loginInDTO.getAccount());
        User user = this.baseMapper.selectOne(queryWrapper);
        if (user == null) {
            // 返回
            throw new BusiException(ErrorCode.PARAM_ERROR, "用户不存在，请注册!");
        }
        // 2.比对传入密码与数据库密码
        String phoneNo = user.getPhoneNumber();
        String password = user.getPassword();
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + loginPassword).getBytes());
        if (!password.equals(encryptPassword)) {
            throw new BusiException(ErrorCode.PARAM_ERROR, "密码错误，请重试密码!");
        }
        // 3. 记录用户的登录态
        request.getSession().setAttribute(LOGIN_STATE_01, user);
        return LoginOutDTO.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .phoneNumber(user.getPhoneNumber())
                .username(user.getUsername())
                .userRole(user.getUserRole())
                .nickname(user.getNickname())
                .createdTime(user.getCreatedTime())
                .updatedTime(user.getUpdatedTime())
                .build();
    }

    @Override
    public SendCodeOutDTO send(SendCodeInDTO sendCodeInDTO, HttpServletRequest request) {
        SendCodeOutDTO out = new SendCodeOutDTO();
        // 1.获取手机号
        String phone = sendCodeInDTO.getPhone();
        if (StringUtils.isAnyBlank(phone)) {
            throw new BusiException(ErrorCode.PARAM_ERROR, "参数为空");
        }
        if (PatternUtils.ifPhoneMatch(phone)) {
            throw new BusiException(ErrorCode.PARAM_ERROR, "手机号格式错误！");
        }
        String code = RandomUtil.randomNumbers(6);
        // 2.根据手机号调用阿里云api
        String templateParam = "{\"code\":" + code + "}";
        AsyncClient client = aliyunSmsClientFactory.createSmsClient();
        SendSmsRequest sendSmsRequest = aliyunSmsClientFactory.getSendSmsRequest(phone, templateParam);
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        SendSmsResponse resp = null;
        try {
            resp = response.get();
        } catch (InterruptedException | ExecutionException e) {
            out.setMsg("发送短信验证码失败");
            e.printStackTrace();
        }
        log.info("发送短信验证码成功. {}", new Gson().toJson(resp));
        client.close();
        Gson gson = new Gson();
        String returnCode = gson.toJson(resp);
        JSONObject json = JSONObject.parseObject(returnCode);
        String resultCode = json.getJSONObject("body").getString("code");
        String resultMsg = json.getJSONObject("body").getString("message");
        if ("OK".equals(resultCode)) {
            // 3.将短信验证码存到session中
            request.getSession().setAttribute("code", code);
            out.setMsg("发送短信验证码成功");
        } else {
            throw new BusiException(ErrorCode.SYSTEM_ERROR, resultMsg);
        }
        return out;
    }

    @Override
    public LoginOutDTO loginByPhone(LoginByPhoneInDTO loginInDTO, HttpServletRequest request) {
        String phone = loginInDTO.getPhone();
        String code = loginInDTO.getCode();
        if (StringUtils.isAnyBlank(phone, code)) {
            throw new BusiException(ErrorCode.PARAM_ERROR, "参数为空");
        }
        // 判断手机号是否正确
        if (PatternUtils.ifPhoneMatch(phone)) {
            throw new BusiException(ErrorCode.PARAM_ERROR, "手机号提示不正确");
        }
        String redisCode = request.getSession().getAttribute("code").toString();
        // 校验手机号
        if (redisCode == null || !redisCode.equals(code)) {
            throw new BusiException(ErrorCode.PARAM_ERROR, "验证码不正确！");
        }
        // 判断用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone_number", phone);
        User user = baseMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new BusiException(ErrorCode.PARAM_ERROR, "用户不存在, 请前往注册页进行注册");
        }
        // 3. 记录用户的登录态
        request.getSession().setAttribute(LOGIN_STATE_01, user);
        return LoginOutDTO.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .phoneNumber(user.getPhoneNumber())
                .username(user.getUsername())
                .userRole(user.getUserRole())
                .nickname(user.getNickname())
                .createdTime(user.getCreatedTime())
                .updatedTime(user.getUpdatedTime())
                .build();
    }

    @Override
    public String register(RegisterInDTO registerInDTO, HttpServletRequest request) {
        String userAccount = registerInDTO.getUserAccount();
        String password = registerInDTO.getPassword();
        String confirmPassword = registerInDTO.getConfirmPassword();
        String phone = registerInDTO.getPhoneNo();
        String code = registerInDTO.getCode();
        if (StringUtils.isAnyBlank(userAccount, password, confirmPassword, phone, code)) {
            throw new BusiException(ErrorCode.PARAM_ERROR, "参数为空");
        }
        if (PatternUtils.ifUserAcctMatch(userAccount)) {
            throw new BusiException(ErrorCode.PARAM_ERROR, "账户格式不正确");
        }
        if (password.length() < 6) {
            throw new BusiException(ErrorCode.PARAM_ERROR, "密码过短");
        }
        if (password.length() > 30) {
            throw new BusiException(ErrorCode.PARAM_ERROR, "密码过长");
        }
        // 校验前后两次密码是否一致
        if (!password.equals(confirmPassword)) {
            throw new BusiException(ErrorCode.PARAM_ERROR, "两次输入的密码不一致");
        }
        String redisCode = request.getSession().getAttribute("code").toString();
        if (redisCode == null || !redisCode.equals(code)) {
            throw new BusiException(ErrorCode.PARAM_ERROR, "验证码不正确");
        }
        // 加密后密码
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        // 查询是否已有用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userAccount);
        long count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusiException(ErrorCode.PARAM_ERROR, "您以创建过该用户，请直接登录");
        }
        // 保存用户
        User user = new User();
        user.setUserId(generateUniqueId());
        user.setUsername(userAccount);
        user.setPassword(encryptPassword);
        user.setPhoneNumber(phone);
        boolean result = save(user);
        if (!result) {
            throw new BusiException(ErrorCode.SYSTEM_ERROR, "注册失败，请联系管理员");
        }
        request.getSession().setAttribute(LOGIN_STATE_00, user);
        return user.getUserId();
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(LOGIN_STATE_01);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusiException(ErrorCode.LOGIN_ERROR);
        }
        // 从数据库查询（追求性能的话可以注释，直接走缓存）
        long userId = currentUser.getId();
        currentUser = this.getById(userId);
        if (currentUser == null) {
            throw new BusiException(ErrorCode.LOGIN_ERROR);
        }
        return currentUser;
    }
}

package com.harper.template.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.template.entity
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-11-25  15:08
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hp_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    public Long id;

    /**
     * 用户ID，不能为空
     */
    public String userId;

    /**
     * 手机号，不能为空
     */
    public String phoneNumber;

    /**
     * 密码，不能为空
     */
    public String password;

    /**
     * 用户名，不能为空
     */
    public String username;

    /**
     * 昵称，默认空字符串
     */
    public String nickname;

    /**
     * 用户简介，默认空字符串
     */
    public String userBio;

    /**
     * 用户角色
     */
    public String userRole;

    /**
     * 创建时间
     */
    public LocalDateTime createdTime;

    /**
     * 更新时间
     */
    public LocalDateTime updatedTime;

    /**
     * 用户头像，默认空字符串
     */
    public String userAvatar;

    /**
     * 微信ID，默认空字符串
     */
    public String wechatId;

    /**
     * 微信昵称，默认空字符串
     */
    public String wechatNickname;
}

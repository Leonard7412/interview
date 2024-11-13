package com.harper.interview.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.interview.entity
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-11-25  15:08
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "harper_user")
public class User implements Serializable {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 账号
     */
    @TableField(value = "userAccount")
    private String userAccount;

    /**
     * 密码
     */
    @TableField(value = "userPassword")
    private String userPassword;

    /**
     * 微信开放平台id
     */
    @TableField(value = "unionId")
    private String unionId;

    /**
     * 公众号openId
     */
    @TableField(value = "mpOpenId")
    private String mpOpenId;

    /**
     * 用户昵称
     */
    @TableField(value = "userName")
    private String userName;

    /**
     * 用户头像
     */
    @TableField(value = "userAvatar")
    private String userAvatar;

    /**
     * 用户简介
     */
    @TableField(value = "userProfile")
    private String userProfile;

    /**
     * 用户角色：user/admin/ban
     */
    @TableField(value = "userRole")
    private String userRole;

    /**
     * 编辑时间
     */
    @TableField(value = "editTime")
    private Date editTime;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "updateTime")
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableField(value = "isDelete")
    private Byte isDelete;

    /**
     * 手机号
     */
    @TableField(value = "phoneNo")
    private String phoneNo;

    /**
     * 微信扫码登录id
     */
    @TableField(value = "wxId")
    private String wxId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

package com.harper.interview.dto.user;

import cn.hutool.core.util.DesensitizedUtil;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.interview.dto.user
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-11-30  11:36
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@Builder
public class LoginOutDTO {
    public Long id;

    public String userId;

    public String phoneNumber;

    public String password;

    public String username;

    public String nickname;

    public String userBio;

    public String userRole;

    private String token;

    public LocalDateTime createdTime;

    public LocalDateTime updatedTime;
}

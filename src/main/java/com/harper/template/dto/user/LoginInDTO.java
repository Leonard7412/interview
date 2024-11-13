package com.harper.template.dto.user;

import lombok.Data;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.template.dto.user
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-11-30  11:22
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class LoginInDTO {
    private String account;

    private String password;
}

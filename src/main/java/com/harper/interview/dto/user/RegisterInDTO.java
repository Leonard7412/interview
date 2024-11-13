package com.harper.interview.dto.user;

import lombok.Data;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.interview.dto.user
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-12-04  14:13
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class RegisterInDTO {
    private String userAccount;

    private String password;

    private String confirmPassword;

    private String phoneNo;

    private String code;
}

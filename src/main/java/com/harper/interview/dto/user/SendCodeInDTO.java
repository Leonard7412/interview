package com.harper.interview.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.interview.dto.user
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-11-30  15:14
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class SendCodeInDTO {
    @NotBlank(message = "手机号不能为空")
    private String phone;
}

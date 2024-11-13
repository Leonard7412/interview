package com.harper.interview.dto.user;

import lombok.Data;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.interview.dto.user
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-12-12  10:45
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class SendSampleInDTO {
    private String recipient;
    private String subject;
    private String content;
    private String[] cc;
    private String filePath;
}

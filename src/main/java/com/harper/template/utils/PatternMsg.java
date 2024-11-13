package com.harper.template.utils;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.template.utils
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-11-30  16:07
 * @Description: TODO
 * @Version: 1.0
 */
public abstract class PatternMsg {
    public static final String PHONE_REGEX = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";

    public static final String USERACCTOUNT_REGEX = "^[A-Za-z0-9]{6,20}$";
}

package com.harper.template.utils;

import cn.hutool.core.util.StrUtil;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.template.utils
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-11-30  16:07
 * @Description: TODO
 * @Version: 1.0
 */
public class PatternUtils {
    // 校验手机号格式
    public static boolean ifPhoneMatch(String phone) {
        return ifMatch(phone, PatternMsg.PHONE_REGEX);
    }

    // 校验账号格式
    public static boolean ifUserAcctMatch(String userAccount) {
        return ifMatch(userAccount, PatternMsg.USERACCTOUNT_REGEX);
    }

    private static boolean ifMatch(String str, String pattern) {
        if (StrUtil.isBlank(str)) {
            return true;
        }
        return !str.matches(pattern);
    }
}

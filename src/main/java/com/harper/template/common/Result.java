package com.harper.template.common;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.template.common
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-11-28  16:05
 * @Description: TODO
 * @Version: 1.0
 */
public class Result {
    public static <T> BaseResult<T> ok(T data) {
        return new BaseResult<>(0, data, "ok");
    }

    public static BaseResult<Void> error(int code, String message) {
        return new BaseResult<>(code, null, message);
    }

    public static BaseResult<Void> error(ErrorCode errorCode, String message) {
        return new BaseResult<>(errorCode.getCode(), null, message);
    }
}


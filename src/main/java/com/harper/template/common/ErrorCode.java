package com.harper.template.common;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.template.common
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-11-25  15:48
 * @Description: TODO
 * @Version: 1.0
 */
public enum ErrorCode {
    /**
     * 成功，对应的错误码为0，错误信息为"OK"。
     */
    SUCCESS(0, "OK"),

    /**
     * 系统内部异常，对应的错误码为10000，错误信息为"系统内部异常"。
     */
    SYSTEM_ERROR(10000, "系统内部异常"),

    /**
     * 操作异常，对应的错误码为10001，错误信息为"操作异常"。
     */
    OPER_ERROR(10001, "操作异常"),

    /**
     * 权限异常，对应的错误码为10002，错误信息为"权限异常"。
     */
    AUTH_EROOR(10002, "权限异常"),

    /**
     * 参数异常，对应的错误码为10003，错误信息为"参数异常"。
     */
    PARAM_ERROR(10003, "参数异常"),

    /**
     * 登录异常，对应的错误码为10004，错误信息为"登录异常"。
     */
    LOGIN_ERROR(10004, "登录异常");

    protected final int code;

    protected final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取当前枚举值的错误码。
     */
    public int getCode() {
        return code;
    }

    /**
     * 获取当前枚举值的错误信息。
     */
    public String getMessage() {
        return message;
    }
}

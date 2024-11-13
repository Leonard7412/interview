package com.harper.interview.common;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.interview.common
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-11-28  15:49
 * @Description: TODO
 * @Version: 1.0
 */
public class BusiException extends RuntimeException {

    protected final int code;

    public BusiException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusiException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}

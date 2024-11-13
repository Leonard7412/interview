package com.harper.template.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.template.common
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-11-25  16:03
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class BaseResult<T> implements Serializable {
    private int code;

    private T data;

    private String message;

    public BaseResult(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
}

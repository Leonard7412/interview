package com.harper.interview.check;

import com.harper.interview.common.BaseResult;
import com.harper.interview.common.BusiException;
import com.harper.interview.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.interview.check
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-12-04  16:51
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalException {
    @ExceptionHandler(BusiException.class)
    public BaseResult<?> busiException(BusiException e) {
        log.error("busiException", e);
        return Result.error(e.getCode(), e.getMessage());
    }
}

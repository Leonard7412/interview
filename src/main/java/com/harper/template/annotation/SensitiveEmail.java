package com.harper.template.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.*;

/**
 * @Author leo.harper
 * @Description 邮箱脱敏
 * @Date 17:37 2024/5/15
 * @Param
 * @return
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(strategy = com.harper.template.strategy.SensitiveEmail.class, pattern = "(\\w+)\\w{3}@(\\w+)", replaceChar = "$1***@$2")
@JacksonAnnotationsInside
public @interface SensitiveEmail {

}

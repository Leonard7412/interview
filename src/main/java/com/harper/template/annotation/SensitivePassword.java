package com.harper.template.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author leo.harper
 * @Description 密码脱敏
 * @Date 17:37 2024/5/15
 * @Param
 * @return
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(strategy = com.harper.template.strategy.SensitivePassword.class, pattern = "(?<=).", replaceChar = "*")
@JacksonAnnotationsInside
public @interface SensitivePassword {

}

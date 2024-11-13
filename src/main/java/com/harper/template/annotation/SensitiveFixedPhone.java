package com.harper.template.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.*;

/**
 * @Author leo.harper
 * @Description 座机号脱敏
 * @Date 17:37 2024/5/15
 * @Param
 * @return
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(strategy = com.harper.template.strategy.SensitiveFixedPhone.class, pattern = "(?<=\\w{0})\\w(?=\\w{4})", replaceChar = "*")
@JacksonAnnotationsInside
public @interface SensitiveFixedPhone {

}

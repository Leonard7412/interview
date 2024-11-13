package com.harper.interview.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.*;

/**
 * @Author leo.harper
 * @Description 地址脱敏
 * @Date 17:35 2024/5/15
 * @Param
 * @return
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(strategy = com.harper.interview.strategy.SensitiveAddress.class, pattern = "(.{5}).+(.{4})", replaceChar = "$1*****$2")
@JacksonAnnotationsInside
public @interface SensitiveAddress {

}

package com.harper.interview.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.*;

/**
 * @Author leo.harper
 * @Description 中文姓名脱敏
 * @Date 17:38 2024/5/15
 * @Param
 * @return
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(strategy = com.harper.interview.strategy.SensitiveChineseName.class, pattern = "(?<=.{1}).", replaceChar = "*")
@JacksonAnnotationsInside
public @interface SensitiveChineseName {


}

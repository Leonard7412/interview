package com.harper.interview.common;

/**
 * @Author leo.harper
 * @Description 抛异常工具类
 * @Date 14:25 2024/11/13
 * @Param
 * @return
 **/
public class ThrowUtils {

    /**
     * @return void
     * @Author leo.harper
     * @Description 条件成立则抛异常
     * @Date 14:25 2024/11/13
     * @Param [condition, runtimeException]
     **/
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     * @return void
     * @Author leo.harper
     * @Description 条件成立则抛异常
     * @Date 14:25 2024/11/13
     * @Param [condition, errorCode]
     **/
    public static void throwIf(boolean condition, ErrorCode errorCode) {
        throwIf(condition, new BusiException(errorCode));
    }

    /**
     * @return void
     * @Author leo.harper
     * @Description 条件成立则抛异常
     * @Date 14:25 2024/11/13
     * @Param [condition, errorCode, message]
     **/
    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        throwIf(condition, new BusiException(errorCode, message));
    }
}

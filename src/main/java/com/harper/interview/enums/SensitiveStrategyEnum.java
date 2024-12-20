package com.harper.interview.enums;

import lombok.Getter;

/**
 * @Author leo.harper
 * @Description 脱敏策略正则
 * @Date 17:30 2024/5/15
 * @Param
 * @return
 **/
public enum SensitiveStrategyEnum {

    /**
     * 中文姓名
     */
    CHINESE_NAME("(?<=.{1}).", "*"),

    /**
     * 密码
     */
    PASSWORD("(?<=).", "*"),

    /**
     * 身份证号
     */
    ID_CARD_NUM("(?<=\\w{0})\\w(?=\\w{4})", "*"),

    /**
     * 固定电话
     */
    FIXED_PHONE("(?<=\\w{0})\\w(?=\\w{4})", "*"),

    /**
     * 电话
     */
    MOBILE("(?<=\\w{3})\\w(?=\\w{4})", "*"),

    /**
     * 地址
     */
    ADDRESS("(.{5}).+(.{4})", "$1*****$2"),
    /**
     * 邮箱
     */
    EMAIL("(\\w+)\\w{3}@(\\w+)", "$1***@$2"),

    /**
     * 银行卡号
     */
    BANKCARD("(?<=\\w{4})\\w(?=\\w{4})", "*"),

    /**
     * 默认策略
     */
    DEFAULT_STRATEGY("", "");

    SensitiveStrategyEnum(String pattern, String replaceChar) {
        this.pattern = pattern;
        this.replaceChar = replaceChar;
    }

    /**
     * 正则-输入格式(1,2,2)
     */
    @Getter
    private String pattern;

    /**
     * 替换后的字符
     */
    @Getter
    private String replaceChar;

}

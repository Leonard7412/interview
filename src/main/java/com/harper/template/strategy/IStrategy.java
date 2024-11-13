package com.harper.template.strategy;


import com.harper.template.utils.SensitiveInfoUtils;

/**
 * @Author leo.harper
 * @Description 脱敏策略
 * @Date 17:39 2024/5/15
 * @Param
 * @return
 **/
public interface IStrategy {

    /**
     * 脱敏的具体实现方法
     *
     * @param source 原来对象属性
     * @param begin  内容开始显示的长度
     * @param end    内容末尾显示的长度
     * @return 返回脱敏后的信息
     */
    String desensitization(final String source, int begin, int end);

    /**
     * 脱敏的具体实现方法
     *
     * @param source      原来对象属性
     * @param pattern     内容显示正则
     * @param replaceChar 替换后的字符
     * @return 返回脱敏后的信息
     */
    default String desensitizationByPattern(String source, String pattern, String replaceChar) {
        return SensitiveInfoUtils.patternReplace(source, pattern, replaceChar);
    }

}

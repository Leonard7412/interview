package com.harper.template.strategy;


import com.harper.template.enums.SensitiveDefaultLengthEnum;
import com.harper.template.utils.SensitiveInfoUtils;

/**
 * @Author leo.harper
 * @Description 默认脱敏策略--脱敏
 * @Date 17:39 2024/5/15
 * @Param
 * @return
 **/
public class DefaultSensitiveStrategy implements IStrategy {

    @Override
    public String desensitization(String source, int begin, int end) {
        return SensitiveInfoUtils.password(source, SensitiveDefaultLengthEnum.DEFAULT_STRATEGY.getBegin());
    }
}

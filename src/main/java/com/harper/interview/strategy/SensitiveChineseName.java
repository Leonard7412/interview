package com.harper.interview.strategy;


import com.harper.interview.enums.SensitiveDefaultLengthEnum;
import com.harper.interview.utils.SensitiveInfoUtils;

/**
 * @Author leo.harper
 * @Description 中文名称脱敏
 * @Date 17:39 2024/5/15
 * @Param
 * @return
 **/
public class SensitiveChineseName implements IStrategy {

    @Override
    public String desensitization(String source, int begin, int end) {
        if (begin != SensitiveDefaultLengthEnum.CHINESE_NAME.getBegin() && begin != 0) {
            return SensitiveInfoUtils.chineseName(source, begin);
        }
        return SensitiveInfoUtils.chineseName(source, SensitiveDefaultLengthEnum.CHINESE_NAME.getBegin());
    }

}

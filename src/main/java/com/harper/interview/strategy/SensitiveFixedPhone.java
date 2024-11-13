package com.harper.interview.strategy;


import com.harper.interview.enums.SensitiveDefaultLengthEnum;
import com.harper.interview.utils.SensitiveInfoUtils;

/**
 * @Author leo.harper
 * @Description 固话脱敏
 * @Date 17:40 2024/5/15
 * @Param
 * @return
 **/
public class SensitiveFixedPhone implements IStrategy {

    @Override
    public String desensitization(String fixedPhone, int begin, int end) {
        if (begin != SensitiveDefaultLengthEnum.FIXED_PHONE.getBegin() && begin != 0 &&
                end != SensitiveDefaultLengthEnum.FIXED_PHONE.getEnd() && end != 0) {
            return SensitiveInfoUtils.fixedPhone(fixedPhone, end);
        }
        return SensitiveInfoUtils.fixedPhone(fixedPhone, SensitiveDefaultLengthEnum.FIXED_PHONE.getEnd());
    }

}

package com.harper.template.strategy;


import com.harper.template.enums.SensitiveDefaultLengthEnum;
import com.harper.template.utils.SensitiveInfoUtils;

/**
 * @Author leo.harper
 * @Description 手机号码脱敏
 * @Date 17:40 2024/5/15
 * @Param
 * @return
 **/
public class SensitiveMobile implements IStrategy {

    @Override
    public String desensitization(String mobile, int begin, int end) {
        if (begin != SensitiveDefaultLengthEnum.MOBILE.getBegin() && begin != 0 &&
                end != SensitiveDefaultLengthEnum.MOBILE.getEnd() && end != 0) {
            return SensitiveInfoUtils.mobilePhone(mobile, begin, end);
        }
        return SensitiveInfoUtils.mobilePhone(mobile, SensitiveDefaultLengthEnum.MOBILE.getBegin(), SensitiveDefaultLengthEnum.MOBILE.getEnd());
    }

}

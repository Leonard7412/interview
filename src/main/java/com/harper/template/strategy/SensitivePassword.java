package com.harper.template.strategy;


import com.harper.template.enums.SensitiveDefaultLengthEnum;
import com.harper.template.utils.SensitiveInfoUtils;

/**
 * @Author leo.harper
 * @Description 密码脱敏
 * @Date 17:40 2024/5/15
 * @Param
 * @return
 **/
public class SensitivePassword implements IStrategy {

    @Override
    public String desensitization(String password, int begin, int end) {
        if (begin != SensitiveDefaultLengthEnum.PASSWORD.getBegin() && begin != 0) {
            return SensitiveInfoUtils.password(password, begin);
        }
        return SensitiveInfoUtils.password(password, SensitiveDefaultLengthEnum.PASSWORD.getBegin());
    }

}

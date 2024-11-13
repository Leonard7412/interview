package com.harper.interview.strategy;


import com.harper.interview.enums.SensitiveDefaultLengthEnum;
import com.harper.interview.utils.SensitiveInfoUtils;

/**
 * @Author leo.harper
 * @Description 电子邮箱脱敏
 * @Date 17:35 2024/5/15
 * @Param
 * @return
 **/
public class SensitiveEmail implements IStrategy {

    @Override
    public String desensitization(String email,int begin,int end) {
        if(begin != SensitiveDefaultLengthEnum.EMAIL.getBegin() && begin !=0 ){
            return SensitiveInfoUtils.email(email,begin);
        }
        return SensitiveInfoUtils.email(email, SensitiveDefaultLengthEnum.EMAIL.getBegin());
    }

}

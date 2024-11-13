package com.harper.template.strategy;


import com.harper.template.enums.SensitiveDefaultLengthEnum;
import com.harper.template.utils.SensitiveInfoUtils;

/**
 * @Author leo.harper
 * @Description 地址脱敏
 * @Date 17:39 2024/5/15
 * @Param
 * @return
 **/
public class SensitiveAddress implements IStrategy {

    @Override
    public String desensitization(String address, int begin, int end) {
        if (begin != SensitiveDefaultLengthEnum.ADDRESS.getBegin() && begin != 0) {
            return SensitiveInfoUtils.address(address, begin);
        }
        return SensitiveInfoUtils.address(address, SensitiveDefaultLengthEnum.ADDRESS.getBegin());
    }

}
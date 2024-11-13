package com.harper.interview.strategy;


import com.harper.interview.enums.SensitiveDefaultLengthEnum;
import com.harper.interview.utils.SensitiveInfoUtils;

/**
 * @Author leo.harper
 * @Description 身份证号脱敏
 * @Date 17:40 2024/5/15
 * @Param
 * @return
 **/
public class SensitiveIdCard implements IStrategy {

    @Override
    public String desensitization(String idCardNum, int begin, int end) {
        if (begin != SensitiveDefaultLengthEnum.ID_CARD_NUM.getBegin() && begin != 0 &&
                end != SensitiveDefaultLengthEnum.ID_CARD_NUM.getEnd() && end != 0) {
            return SensitiveInfoUtils.idCardNum(idCardNum, begin, end);
        }
        return SensitiveInfoUtils.idCardNum(idCardNum, SensitiveDefaultLengthEnum.ID_CARD_NUM.getBegin(), SensitiveDefaultLengthEnum.ID_CARD_NUM.getEnd());
    }

}

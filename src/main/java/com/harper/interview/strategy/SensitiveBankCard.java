package com.harper.interview.strategy;


import com.harper.interview.enums.SensitiveDefaultLengthEnum;
import com.harper.interview.utils.SensitiveInfoUtils;

/**
 * @Author leo.harper
 * @Description 银行卡号脱敏
 * @Date 17:39 2024/5/15
 * @Param
 * @return
 **/
public class SensitiveBankCard implements IStrategy {

    @Override
    public String desensitization(String bankCard, int begin, int end) {
        if (begin != SensitiveDefaultLengthEnum.BANKCARD.getBegin() && begin != 0 &&
                end != SensitiveDefaultLengthEnum.BANKCARD.getEnd() && end != 0) {
            return SensitiveInfoUtils.bankCard(bankCard, begin, end);
        }
        return SensitiveInfoUtils.bankCard(bankCard, SensitiveDefaultLengthEnum.BANKCARD.getBegin(), SensitiveDefaultLengthEnum.BANKCARD.getEnd());
    }

}

package com.harper.interview.vo;


import com.harper.interview.entity.HarperQuestionBank;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题库视图
 */
@Data
public class HarperQuestionBankVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 标签列表
     */
    private List<String> tagList;

    /**
     * 封装类转对象
     *
     * @param harperQuestionBankVO
     * @return
     */
    public static HarperQuestionBank voToObj(HarperQuestionBankVO harperQuestionBankVO) {
        if (harperQuestionBankVO == null) {
            return null;
        }
        HarperQuestionBank harperQuestionBank = new HarperQuestionBank();
        BeanUtils.copyProperties(harperQuestionBankVO, harperQuestionBank);
        List<String> tagList = harperQuestionBankVO.getTagList();

        return harperQuestionBank;
    }

    /**
     * 对象转封装类
     *
     * @param harperQuestionBank
     * @return
     */
    public static HarperQuestionBankVO objToVo(HarperQuestionBank harperQuestionBank) {
        if (harperQuestionBank == null) {
            return null;
        }
        HarperQuestionBankVO harperQuestionBankVO = new HarperQuestionBankVO();
        BeanUtils.copyProperties(harperQuestionBank, harperQuestionBankVO);

        return harperQuestionBankVO;
    }
}

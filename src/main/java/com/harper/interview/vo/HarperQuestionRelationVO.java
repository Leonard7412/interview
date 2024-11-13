package com.harper.interview.vo;


import com.harper.interview.entity.HarperQuestionRelation;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题库题目关联视图
 */
@Data
public class HarperQuestionRelationVO implements Serializable {

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
     * @param harperQuestionRelationVO
     * @return
     */
    public static HarperQuestionRelation voToObj(HarperQuestionRelationVO harperQuestionRelationVO) {
        if (harperQuestionRelationVO == null) {
            return null;
        }
        HarperQuestionRelation harperQuestionRelation = new HarperQuestionRelation();
        BeanUtils.copyProperties(harperQuestionRelationVO, harperQuestionRelation);
        List<String> tagList = harperQuestionRelationVO.getTagList();
        return harperQuestionRelation;
    }

    /**
     * 对象转封装类
     *
     * @param harperQuestionRelation
     * @return
     */
    public static HarperQuestionRelationVO objToVo(HarperQuestionRelation harperQuestionRelation) {
        if (harperQuestionRelation == null) {
            return null;
        }
        HarperQuestionRelationVO harperQuestionRelationVO = new HarperQuestionRelationVO();
        BeanUtils.copyProperties(harperQuestionRelation, harperQuestionRelationVO);
        return harperQuestionRelationVO;
    }
}

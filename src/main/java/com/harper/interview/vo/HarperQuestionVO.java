package com.harper.interview.vo;

import cn.hutool.json.JSONUtil;
import com.harper.interview.entity.HarperQuestion;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目视图
 */
@Data
public class HarperQuestionVO implements Serializable {

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
     * @param harperQuestionVO
     * @return
     */
    public static HarperQuestion voToObj(HarperQuestionVO harperQuestionVO) {
        if (harperQuestionVO == null) {
            return null;
        }
        HarperQuestion harperQuestion = new HarperQuestion();
        BeanUtils.copyProperties(harperQuestionVO, harperQuestion);
        List<String> tagList = harperQuestionVO.getTagList();
        harperQuestion.setTags(JSONUtil.toJsonStr(tagList));
        return harperQuestion;
    }

    /**
     * 对象转封装类
     *
     * @param harperQuestion
     * @return
     */
    public static HarperQuestionVO objToVo(HarperQuestion harperQuestion) {
        if (harperQuestion == null) {
            return null;
        }
        HarperQuestionVO harperQuestionVO = new HarperQuestionVO();
        BeanUtils.copyProperties(harperQuestion, harperQuestionVO);
        harperQuestionVO.setTagList(JSONUtil.toList(harperQuestion.getTags(), String.class));
        return harperQuestionVO;
    }
}

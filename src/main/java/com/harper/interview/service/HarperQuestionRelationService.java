package com.harper.interview.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harper.interview.dto.harperQuestionRelation.HarperQuestionRelationQueryRequest;
import com.harper.interview.entity.HarperQuestionRelation;
import com.harper.interview.vo.HarperQuestionRelationVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 题库题目关联服务
 *
 */
public interface HarperQuestionRelationService extends IService<HarperQuestionRelation> {

    /**
     * 校验数据
     *
     * @param harperQuestionRelation
     * @param add 对创建的数据进行校验
     */
    void validHarperQuestionRelation(HarperQuestionRelation harperQuestionRelation, boolean add);

    /**
     * 获取查询条件
     *
     * @param harperQuestionRelationQueryRequest
     * @return
     */
    QueryWrapper<HarperQuestionRelation> getQueryWrapper(HarperQuestionRelationQueryRequest harperQuestionRelationQueryRequest);

    /**
     * 获取题库题目关联封装
     *
     * @param harperQuestionRelation
     * @param request
     * @return
     */
    HarperQuestionRelationVO getHarperQuestionRelationVO(HarperQuestionRelation harperQuestionRelation, HttpServletRequest request);

    /**
     * 分页获取题库题目关联封装
     *
     * @param harperQuestionRelationPage
     * @param request
     * @return
     */
    Page<HarperQuestionRelationVO> getHarperQuestionRelationVOPage(Page<HarperQuestionRelation> harperQuestionRelationPage, HttpServletRequest request);
}

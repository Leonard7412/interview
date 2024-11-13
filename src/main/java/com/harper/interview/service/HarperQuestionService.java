package com.harper.interview.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harper.interview.dto.harperQuestion.HarperQuestionQueryRequest;
import com.harper.interview.entity.HarperQuestion;
import com.harper.interview.vo.HarperQuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 题目服务
 *
 */
public interface HarperQuestionService extends IService<HarperQuestion> {

    /**
     * 校验数据
     *
     * @param harperQuestion
     * @param add 对创建的数据进行校验
     */
    void validHarperQuestion(HarperQuestion harperQuestion, boolean add);

    /**
     * 获取查询条件
     *
     * @param harperQuestionQueryRequest
     * @return
     */
    QueryWrapper<HarperQuestion> getQueryWrapper(HarperQuestionQueryRequest harperQuestionQueryRequest);

    /**
     * 获取题目封装
     *
     * @param harperQuestion
     * @param request
     * @return
     */
    HarperQuestionVO getHarperQuestionVO(HarperQuestion harperQuestion, HttpServletRequest request);

    /**
     * 分页获取题目封装
     *
     * @param harperQuestionPage
     * @param request
     * @return
     */
    Page<HarperQuestionVO> getHarperQuestionVOPage(Page<HarperQuestion> harperQuestionPage, HttpServletRequest request);
}

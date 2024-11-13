package com.harper.interview.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harper.interview.dto.harperQuestionBank.HarperQuestionBankQueryRequest;
import com.harper.interview.entity.HarperQuestionBank;
import com.harper.interview.vo.HarperQuestionBankVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 题库服务
 *
 */
public interface HarperQuestionBankService extends IService<HarperQuestionBank> {

    /**
     * 校验数据
     *
     * @param harperQuestionBank
     * @param add 对创建的数据进行校验
     */
    void validHarperQuestionBank(HarperQuestionBank harperQuestionBank, boolean add);

    /**
     * 获取查询条件
     *
     * @param harperQuestionBankQueryRequest
     * @return
     */
    QueryWrapper<HarperQuestionBank> getQueryWrapper(HarperQuestionBankQueryRequest harperQuestionBankQueryRequest);

    /**
     * 获取题库封装
     *
     * @param harperQuestionBank
     * @param request
     * @return
     */
    HarperQuestionBankVO getHarperQuestionBankVO(HarperQuestionBank harperQuestionBank, HttpServletRequest request);

    /**
     * 分页获取题库封装
     *
     * @param harperQuestionBankPage
     * @param request
     * @return
     */
    Page<HarperQuestionBankVO> getHarperQuestionBankVOPage(Page<HarperQuestionBank> harperQuestionBankPage, HttpServletRequest request);
}

package com.harper.interview.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harper.interview.common.ErrorCode;
import com.harper.interview.common.ThrowUtils;
import com.harper.interview.dto.harperQuestionBank.HarperQuestionBankQueryRequest;
import com.harper.interview.entity.HarperQuestionBank;
import com.harper.interview.factory.UserServiceFactory;
import com.harper.interview.mapper.HarperQuestionBankMapper;
import com.harper.interview.service.HarperQuestionBankService;
import com.harper.interview.vo.HarperQuestionBankVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.harper.interview.check.SqlUtils.validSortField;
import static com.harper.interview.utils.Consts.SORT_ORDER_ASC;

/**
 * 题库服务实现
 */
@Service
@Slf4j
public class HarperQuestionBankServiceImpl extends ServiceImpl<HarperQuestionBankMapper, HarperQuestionBank> implements HarperQuestionBankService {

    @Resource
    private UserServiceFactory userServiceFactory;

    /**
     * 校验数据
     *
     * @param harperQuestionBank
     * @param add                对创建的数据进行校验
     */
    @Override
    public void validHarperQuestionBank(HarperQuestionBank harperQuestionBank, boolean add) {
        ThrowUtils.throwIf(harperQuestionBank == null, ErrorCode.PARAM_ERROR);
        // todo 从对象中取值
        String title = harperQuestionBank.getTitle();
        // 创建数据时，参数不能为空
        if (add) {
            // todo 补充校验规则
            ThrowUtils.throwIf(StringUtils.isBlank(title), ErrorCode.PARAM_ERROR);
        }
        // 修改数据时，有参数则校验
        // todo 补充校验规则
        if (StringUtils.isNotBlank(title)) {
            ThrowUtils.throwIf(title.length() > 80, ErrorCode.PARAM_ERROR, "标题过长");
        }
    }

    /**
     * 获取查询条件
     *
     * @param harperQuestionBankQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<HarperQuestionBank> getQueryWrapper(HarperQuestionBankQueryRequest harperQuestionBankQueryRequest) {
        QueryWrapper<HarperQuestionBank> queryWrapper = new QueryWrapper<>();
        if (harperQuestionBankQueryRequest == null) {
            return queryWrapper;
        }
        // todo 从对象中取值
        Long id = harperQuestionBankQueryRequest.getId();
        Long notId = harperQuestionBankQueryRequest.getNotId();
        String title = harperQuestionBankQueryRequest.getTitle();
        String content = harperQuestionBankQueryRequest.getContent();
        String searchText = harperQuestionBankQueryRequest.getSearchText();
        String sortField = harperQuestionBankQueryRequest.getSortField();
        String sortOrder = harperQuestionBankQueryRequest.getSortOrder();
        List<String> tagList = harperQuestionBankQueryRequest.getTags();
        Long userId = harperQuestionBankQueryRequest.getUserId();
        // todo 补充需要的查询条件
        // 从多字段中搜索
        if (StringUtils.isNotBlank(searchText)) {
            // 需要拼接查询条件
            queryWrapper.and(qw -> qw.like("title", searchText).or().like("content", searchText));
        }
        // 模糊查询
        queryWrapper.like(StringUtils.isNotBlank(title), "title", title);
        queryWrapper.like(StringUtils.isNotBlank(content), "content", content);
        // JSON 数组查询
        if (CollUtil.isNotEmpty(tagList)) {
            for (String tag : tagList) {
                queryWrapper.like("tags", "\"" + tag + "\"");
            }
        }
        // 精确查询
        queryWrapper.ne(ObjectUtils.isNotEmpty(notId), "id", notId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        // 排序规则
        queryWrapper.orderBy(validSortField(sortField),
                sortOrder.equals(SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    /**
     * 获取题库封装
     *
     * @param harperQuestionBank
     * @param request
     * @return
     */
    @Override
    public HarperQuestionBankVO getHarperQuestionBankVO(HarperQuestionBank harperQuestionBank, HttpServletRequest request) {
        // 对象转封装类
        HarperQuestionBankVO harperQuestionBankVO = HarperQuestionBankVO.objToVo(harperQuestionBank);

        // todo 可以根据需要为封装对象补充值，不需要的内容可以删除
        // region 可选
        // 1. 关联查询用户信息
        Long userId = harperQuestionBank.getUserId();
        return harperQuestionBankVO;
    }

    /**
     * 分页获取题库封装
     *
     * @param harperQuestionBankPage
     * @param request
     * @return
     */
    @Override
    public Page<HarperQuestionBankVO> getHarperQuestionBankVOPage(Page<HarperQuestionBank> harperQuestionBankPage, HttpServletRequest request) {
        List<HarperQuestionBank> harperQuestionBankList = harperQuestionBankPage.getRecords();
        Page<HarperQuestionBankVO> harperQuestionBankVOPage = new Page<>(harperQuestionBankPage.getCurrent(), harperQuestionBankPage.getSize(), harperQuestionBankPage.getTotal());
        if (CollUtil.isEmpty(harperQuestionBankList)) {
            return harperQuestionBankVOPage;
        }
        // 对象列表 => 封装对象列表
        List<HarperQuestionBankVO> harperQuestionBankVOList = harperQuestionBankList.stream().map(harperQuestionBank -> {
            return HarperQuestionBankVO.objToVo(harperQuestionBank);
        }).collect(Collectors.toList());

        // todo 可以根据需要为封装对象补充值，不需要的内容可以删除
        // region 可选
        // 1. 关联查询用户信息
        Set<Long> userIdSet = harperQuestionBankList.stream().map(HarperQuestionBank::getUserId).collect(Collectors.toSet());

        harperQuestionBankVOPage.setRecords(harperQuestionBankVOList);
        return harperQuestionBankVOPage;
    }

}

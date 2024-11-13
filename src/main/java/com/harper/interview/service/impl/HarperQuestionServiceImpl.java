package com.harper.interview.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harper.interview.common.ErrorCode;
import com.harper.interview.common.ThrowUtils;
import com.harper.interview.dto.harperQuestion.HarperQuestionQueryRequest;
import com.harper.interview.entity.HarperQuestion;
import com.harper.interview.factory.UserServiceFactory;
import com.harper.interview.mapper.HarperQuestionMapper;
import com.harper.interview.service.HarperQuestionService;
import com.harper.interview.vo.HarperQuestionVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.harper.interview.check.SqlUtils.validSortField;
import static com.harper.interview.utils.Consts.SORT_ORDER_ASC;

/**
 * 题目服务实现
 */
@Service
@Slf4j
public class HarperQuestionServiceImpl extends ServiceImpl<HarperQuestionMapper, HarperQuestion> implements HarperQuestionService {

    @Resource
    private UserServiceFactory userServiceFactory;

    /**
     * 校验数据
     *
     * @param harperQuestion
     * @param add            对创建的数据进行校验
     */
    @Override
    public void validHarperQuestion(HarperQuestion harperQuestion, boolean add) {
        ThrowUtils.throwIf(harperQuestion == null, ErrorCode.PARAM_ERROR);
        // todo 从对象中取值
        String title = harperQuestion.getTitle();
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
     * @param harperQuestionQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<HarperQuestion> getQueryWrapper(HarperQuestionQueryRequest harperQuestionQueryRequest) {
        QueryWrapper<HarperQuestion> queryWrapper = new QueryWrapper<>();
        if (harperQuestionQueryRequest == null) {
            return queryWrapper;
        }
        // todo 从对象中取值
        Long id = harperQuestionQueryRequest.getId();
        Long notId = harperQuestionQueryRequest.getNotId();
        String title = harperQuestionQueryRequest.getTitle();
        String content = harperQuestionQueryRequest.getContent();
        String searchText = harperQuestionQueryRequest.getSearchText();
        String sortField = harperQuestionQueryRequest.getSortField();
        String sortOrder = harperQuestionQueryRequest.getSortOrder();
        List<String> tagList = harperQuestionQueryRequest.getTags();
        Long userId = harperQuestionQueryRequest.getUserId();
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
     * 获取题目封装
     *
     * @param harperQuestion
     * @param request
     * @return
     */
    @Override
    public HarperQuestionVO getHarperQuestionVO(HarperQuestion harperQuestion, HttpServletRequest request) {
        // 对象转封装类
        HarperQuestionVO harperQuestionVO = HarperQuestionVO.objToVo(harperQuestion);
        return harperQuestionVO;
    }

    /**
     * 分页获取题目封装
     *
     * @param harperQuestionPage
     * @param request
     * @return
     */
    @Override
    public Page<HarperQuestionVO> getHarperQuestionVOPage(Page<HarperQuestion> harperQuestionPage, HttpServletRequest request) {
        List<HarperQuestion> harperQuestionList = harperQuestionPage.getRecords();
        Page<HarperQuestionVO> harperQuestionVOPage = new Page<>(harperQuestionPage.getCurrent(), harperQuestionPage.getSize(), harperQuestionPage.getTotal());
        if (CollUtil.isEmpty(harperQuestionList)) {
            return harperQuestionVOPage;
        }
        return harperQuestionVOPage;
    }

}

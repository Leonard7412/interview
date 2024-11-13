package com.harper.interview.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harper.interview.common.ErrorCode;
import com.harper.interview.common.ThrowUtils;
import com.harper.interview.dto.harperQuestionRelation.HarperQuestionRelationQueryRequest;
import com.harper.interview.entity.HarperQuestionRelation;
import com.harper.interview.factory.UserServiceFactory;
import com.harper.interview.mapper.HarperQuestionRelationMapper;
import com.harper.interview.service.HarperQuestionRelationService;
import com.harper.interview.vo.HarperQuestionRelationVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 题库题目关联服务实现
 */
@Service
@Slf4j
public class HarperQuestionRelationServiceImpl extends ServiceImpl<HarperQuestionRelationMapper, HarperQuestionRelation> implements HarperQuestionRelationService {

    @Resource
    private UserServiceFactory userServiceFactory;

    /**
     * 校验数据
     *
     * @param harperQuestionRelation
     * @param add                    对创建的数据进行校验
     */
    @Override
    public void validHarperQuestionRelation(HarperQuestionRelation harperQuestionRelation, boolean add) {
        ThrowUtils.throwIf(harperQuestionRelation == null, ErrorCode.PARAM_ERROR);
    }

    /**
     * 获取查询条件
     *
     * @param harperQuestionRelationQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<HarperQuestionRelation> getQueryWrapper(HarperQuestionRelationQueryRequest harperQuestionRelationQueryRequest) {
        QueryWrapper<HarperQuestionRelation> queryWrapper = new QueryWrapper<>();
        if (harperQuestionRelationQueryRequest == null) {
            return queryWrapper;
        }
        // todo 从对象中取值
        Long id = harperQuestionRelationQueryRequest.getId();
        Long notId = harperQuestionRelationQueryRequest.getNotId();
        String title = harperQuestionRelationQueryRequest.getTitle();
        String content = harperQuestionRelationQueryRequest.getContent();
        String searchText = harperQuestionRelationQueryRequest.getSearchText();
        List<String> tagList = harperQuestionRelationQueryRequest.getTags();
        Long userId = harperQuestionRelationQueryRequest.getUserId();
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
        return queryWrapper;
    }

    /**
     * 获取题库题目关联封装
     *
     * @param harperQuestionRelation
     * @param request
     * @return
     */
    @Override
    public HarperQuestionRelationVO getHarperQuestionRelationVO(HarperQuestionRelation harperQuestionRelation, HttpServletRequest request) {
        // 对象转封装类
        HarperQuestionRelationVO harperQuestionRelationVO = HarperQuestionRelationVO.objToVo(harperQuestionRelation);

        return harperQuestionRelationVO;
    }

    /**
     * 分页获取题库题目关联封装
     *
     * @param harperQuestionRelationPage
     * @param request
     * @return
     */
    @Override
    public Page<HarperQuestionRelationVO> getHarperQuestionRelationVOPage(Page<HarperQuestionRelation> harperQuestionRelationPage, HttpServletRequest request) {
        List<HarperQuestionRelation> harperQuestionRelationList = harperQuestionRelationPage.getRecords();
        Page<HarperQuestionRelationVO> harperQuestionRelationVOPage = new Page<>(harperQuestionRelationPage.getCurrent(), harperQuestionRelationPage.getSize(), harperQuestionRelationPage.getTotal());
        if (CollUtil.isEmpty(harperQuestionRelationList)) {
            return harperQuestionRelationVOPage;
        }
        // 对象列表 => 封装对象列表
        List<HarperQuestionRelationVO> harperQuestionRelationVOList = harperQuestionRelationList.stream().map(harperQuestionRelation -> {
            return HarperQuestionRelationVO.objToVo(harperQuestionRelation);
        }).collect(Collectors.toList());

        // todo 可以根据需要为封装对象补充值，不需要的内容可以删除
        // region 可选
        // 1. 关联查询用户信息
        Set<Long> userIdSet = harperQuestionRelationList.stream().map(HarperQuestionRelation::getUserId).collect(Collectors.toSet());
        // 填充信息
        harperQuestionRelationVOList.forEach(harperQuestionRelationVO -> {
        });
        // endregion

        harperQuestionRelationVOPage.setRecords(harperQuestionRelationVOList);
        return harperQuestionRelationVOPage;
    }

}

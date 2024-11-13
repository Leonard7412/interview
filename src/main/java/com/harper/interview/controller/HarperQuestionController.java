package com.harper.interview.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harper.interview.common.*;
import com.harper.interview.dto.harperQuestion.HarperQuestionAddRequest;
import com.harper.interview.dto.harperQuestion.HarperQuestionEditRequest;
import com.harper.interview.dto.harperQuestion.HarperQuestionQueryRequest;
import com.harper.interview.dto.harperQuestion.HarperQuestionUpdateRequest;
import com.harper.interview.entity.HarperQuestion;
import com.harper.interview.factory.UserServiceFactory;
import com.harper.interview.service.HarperQuestionService;
import com.harper.interview.vo.HarperQuestionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目接口
 */
@RestController
@RequestMapping("/harperQuestion")
@Slf4j
public class HarperQuestionController {

    @Resource
    private HarperQuestionService harperQuestionService;

    @Resource
    private UserServiceFactory userServiceFactory;

    // region 增删改查

    /**
     * 创建题目
     *
     * @param harperQuestionAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResult<Long> addHarperQuestion(@RequestBody HarperQuestionAddRequest harperQuestionAddRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(harperQuestionAddRequest == null, ErrorCode.PARAM_ERROR);
        // todo 在此处将实体类和 DTO 进行转换
        HarperQuestion harperQuestion = new HarperQuestion();
        BeanUtils.copyProperties(harperQuestionAddRequest, harperQuestion);
        // 数据校验
        harperQuestionService.validHarperQuestion(harperQuestion, true);
        // 写入数据库
        boolean result = harperQuestionService.save(harperQuestion);
        ThrowUtils.throwIf(!result, ErrorCode.OPER_ERROR);
        // 返回新写入的数据 id
        long newHarperQuestionId = harperQuestion.getId();
        return Result.ok(newHarperQuestionId);
    }

    /**
     * 删除题目
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResult<Boolean> deleteHarperQuestion(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusiException(ErrorCode.PARAM_ERROR);
        }
        return Result.ok(true);
    }

    /**
     * 更新题目（仅管理员可用）
     *
     * @param harperQuestionUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public BaseResult<Boolean> updateHarperQuestion(@RequestBody HarperQuestionUpdateRequest harperQuestionUpdateRequest) {
        if (harperQuestionUpdateRequest == null || harperQuestionUpdateRequest.getId() <= 0) {
            throw new BusiException(ErrorCode.PARAM_ERROR);
        }
        // todo 在此处将实体类和 DTO 进行转换
        HarperQuestion harperQuestion = new HarperQuestion();
        BeanUtils.copyProperties(harperQuestionUpdateRequest, harperQuestion);
        // 数据校验
        harperQuestionService.validHarperQuestion(harperQuestion, false);
        // 判断是否存在
        long id = harperQuestionUpdateRequest.getId();
        HarperQuestion oldHarperQuestion = harperQuestionService.getById(id);
        ThrowUtils.throwIf(oldHarperQuestion == null, ErrorCode.NOT_FOUND_ERROR);
        // 操作数据库
        boolean result = harperQuestionService.updateById(harperQuestion);
        ThrowUtils.throwIf(!result, ErrorCode.OPER_ERROR);
        return Result.ok(true);
    }

    /**
     * 根据 id 获取题目（封装类）
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResult<HarperQuestionVO> getHarperQuestionVOById(long id, HttpServletRequest request) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAM_ERROR);
        // 查询数据库
        HarperQuestion harperQuestion = harperQuestionService.getById(id);
        ThrowUtils.throwIf(harperQuestion == null, ErrorCode.NOT_FOUND_ERROR);
        // 获取封装类
        return Result.ok(harperQuestionService.getHarperQuestionVO(harperQuestion, request));
    }

    /**
     * 分页获取题目列表（仅管理员可用）
     *
     * @param harperQuestionQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResult<Page<HarperQuestion>> listHarperQuestionByPage(@RequestBody HarperQuestionQueryRequest harperQuestionQueryRequest) {
        long current = harperQuestionQueryRequest.getCurrent();
        long size = harperQuestionQueryRequest.getPageSize();
        // 查询数据库
        Page<HarperQuestion> harperQuestionPage = harperQuestionService.page(new Page<>(current, size),
                harperQuestionService.getQueryWrapper(harperQuestionQueryRequest));
        return Result.ok(harperQuestionPage);
    }

    /**
     * 分页获取题目列表（封装类）
     *
     * @param harperQuestionQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResult<Page<HarperQuestionVO>> listHarperQuestionVOByPage(@RequestBody HarperQuestionQueryRequest harperQuestionQueryRequest,
                                                                         HttpServletRequest request) {
        long current = harperQuestionQueryRequest.getCurrent();
        long size = harperQuestionQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAM_ERROR);
        // 查询数据库
        Page<HarperQuestion> harperQuestionPage = harperQuestionService.page(new Page<>(current, size),
                harperQuestionService.getQueryWrapper(harperQuestionQueryRequest));
        // 获取封装类
        return Result.ok(harperQuestionService.getHarperQuestionVOPage(harperQuestionPage, request));
    }

    /**
     * 分页获取当前登录用户创建的题目列表
     *
     * @param harperQuestionQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/my/list/page/vo")
    public BaseResult<Page<HarperQuestionVO>> listMyHarperQuestionVOByPage(@RequestBody HarperQuestionQueryRequest harperQuestionQueryRequest,
                                                                           HttpServletRequest request) {
        ThrowUtils.throwIf(harperQuestionQueryRequest == null, ErrorCode.PARAM_ERROR);
        long current = harperQuestionQueryRequest.getCurrent();
        long size = harperQuestionQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAM_ERROR);
        // 查询数据库
        Page<HarperQuestion> harperQuestionPage = harperQuestionService.page(new Page<>(current, size),
                harperQuestionService.getQueryWrapper(harperQuestionQueryRequest));
        // 获取封装类
        return Result.ok(harperQuestionService.getHarperQuestionVOPage(harperQuestionPage, request));
    }

    /**
     * 编辑题目（给用户使用）
     *
     * @param harperQuestionEditRequest
     * @param request
     * @return
     */
    @PostMapping("/edit")
    public BaseResult<Boolean> editHarperQuestion(@RequestBody HarperQuestionEditRequest harperQuestionEditRequest, HttpServletRequest request) {
        if (harperQuestionEditRequest == null || harperQuestionEditRequest.getId() <= 0) {
            throw new BusiException(ErrorCode.PARAM_ERROR);
        }
        // todo 在此处将实体类和 DTO 进行转换
        HarperQuestion harperQuestion = new HarperQuestion();
        BeanUtils.copyProperties(harperQuestionEditRequest, harperQuestion);
        // 数据校验
        harperQuestionService.validHarperQuestion(harperQuestion, false);
        // 操作数据库
        boolean result = harperQuestionService.updateById(harperQuestion);
        ThrowUtils.throwIf(!result, ErrorCode.OPER_ERROR);
        return Result.ok(true);
    }

    // endregion
}

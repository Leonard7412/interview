package com.harper.interview.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harper.interview.common.*;
import com.harper.interview.dto.harperQuestionRelation.HarperQuestionRelationAddRequest;
import com.harper.interview.dto.harperQuestionRelation.HarperQuestionRelationEditRequest;
import com.harper.interview.dto.harperQuestionRelation.HarperQuestionRelationQueryRequest;
import com.harper.interview.dto.harperQuestionRelation.HarperQuestionRelationUpdateRequest;
import com.harper.interview.entity.HarperQuestionRelation;
import com.harper.interview.factory.UserServiceFactory;
import com.harper.interview.service.HarperQuestionRelationService;
import com.harper.interview.service.UserService;
import com.harper.interview.vo.HarperQuestionRelationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题库题目关联接口
 */
@RestController
@RequestMapping("/harperQuestionRelation")
@Slf4j
public class HarperQuestionRelationController {

    @Resource
    private HarperQuestionRelationService harperQuestionRelationService;

    @Resource
    private UserServiceFactory userServiceFactory;

    /**
     * 创建题库题目关联
     *
     * @param harperQuestionRelationAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResult<Long> addHarperQuestionRelation(@RequestBody HarperQuestionRelationAddRequest harperQuestionRelationAddRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(harperQuestionRelationAddRequest == null, ErrorCode.PARAM_ERROR);
        // todo 在此处将实体类和 DTO 进行转换
        HarperQuestionRelation harperQuestionRelation = new HarperQuestionRelation();
        BeanUtils.copyProperties(harperQuestionRelationAddRequest, harperQuestionRelation);
        // 数据校验
        harperQuestionRelationService.validHarperQuestionRelation(harperQuestionRelation, true);
        // 写入数据库
        boolean result = harperQuestionRelationService.save(harperQuestionRelation);
        ThrowUtils.throwIf(!result, ErrorCode.OPER_ERROR);
        // 返回新写入的数据 id
        long newHarperQuestionRelationId = harperQuestionRelation.getId();
        return Result.ok(newHarperQuestionRelationId);
    }

    /**
     * 删除题库题目关联
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResult<Boolean> deleteHarperQuestionRelation(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusiException(ErrorCode.PARAM_ERROR);
        }
        return Result.ok(true);
    }

    /**
     * 更新题库题目关联（仅管理员可用）
     *
     * @param harperQuestionRelationUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public BaseResult<Boolean> updateHarperQuestionRelation(@RequestBody HarperQuestionRelationUpdateRequest harperQuestionRelationUpdateRequest) {
        if (harperQuestionRelationUpdateRequest == null || harperQuestionRelationUpdateRequest.getId() <= 0) {
            throw new BusiException(ErrorCode.PARAM_ERROR);
        }
        // todo 在此处将实体类和 DTO 进行转换
        HarperQuestionRelation harperQuestionRelation = new HarperQuestionRelation();
        BeanUtils.copyProperties(harperQuestionRelationUpdateRequest, harperQuestionRelation);
        // 数据校验
        harperQuestionRelationService.validHarperQuestionRelation(harperQuestionRelation, false);
        // 判断是否存在
        long id = harperQuestionRelationUpdateRequest.getId();
        HarperQuestionRelation oldHarperQuestionRelation = harperQuestionRelationService.getById(id);
        ThrowUtils.throwIf(oldHarperQuestionRelation == null, ErrorCode.NOT_FOUND_ERROR);
        // 操作数据库
        boolean result = harperQuestionRelationService.updateById(harperQuestionRelation);
        ThrowUtils.throwIf(!result, ErrorCode.OPER_ERROR);
        return Result.ok(true);
    }

    /**
     * 根据 id 获取题库题目关联（封装类）
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResult<HarperQuestionRelationVO> getHarperQuestionRelationVOById(long id, HttpServletRequest request) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAM_ERROR);
        // 查询数据库
        HarperQuestionRelation harperQuestionRelation = harperQuestionRelationService.getById(id);
        ThrowUtils.throwIf(harperQuestionRelation == null, ErrorCode.NOT_FOUND_ERROR);
        // 获取封装类
        return Result.ok(harperQuestionRelationService.getHarperQuestionRelationVO(harperQuestionRelation, request));
    }

    /**
     * 分页获取题库题目关联列表（仅管理员可用）
     *
     * @param harperQuestionRelationQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResult<Page<HarperQuestionRelation>> listHarperQuestionRelationByPage(@RequestBody HarperQuestionRelationQueryRequest harperQuestionRelationQueryRequest) {
        long current = harperQuestionRelationQueryRequest.getCurrent();
        long size = harperQuestionRelationQueryRequest.getPageSize();
        // 查询数据库
        Page<HarperQuestionRelation> harperQuestionRelationPage = harperQuestionRelationService.page(new Page<>(current, size),
                harperQuestionRelationService.getQueryWrapper(harperQuestionRelationQueryRequest));
        return Result.ok(harperQuestionRelationPage);
    }

    /**
     * 分页获取题库题目关联列表（封装类）
     *
     * @param harperQuestionRelationQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResult<Page<HarperQuestionRelationVO>> listHarperQuestionRelationVOByPage(@RequestBody HarperQuestionRelationQueryRequest harperQuestionRelationQueryRequest,
                                                                                         HttpServletRequest request) {
        long current = harperQuestionRelationQueryRequest.getCurrent();
        long size = harperQuestionRelationQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAM_ERROR);
        // 查询数据库
        Page<HarperQuestionRelation> harperQuestionRelationPage = harperQuestionRelationService.page(new Page<>(current, size),
                harperQuestionRelationService.getQueryWrapper(harperQuestionRelationQueryRequest));
        // 获取封装类
        return Result.ok(harperQuestionRelationService.getHarperQuestionRelationVOPage(harperQuestionRelationPage, request));
    }

    /**
     * 分页获取当前登录用户创建的题库题目关联列表
     *
     * @param harperQuestionRelationQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/my/list/page/vo")
    public BaseResult<Page<HarperQuestionRelationVO>> listMyHarperQuestionRelationVOByPage(@RequestBody HarperQuestionRelationQueryRequest harperQuestionRelationQueryRequest,
                                                                                           HttpServletRequest request) {
        ThrowUtils.throwIf(harperQuestionRelationQueryRequest == null, ErrorCode.PARAM_ERROR);
        long current = harperQuestionRelationQueryRequest.getCurrent();
        long size = harperQuestionRelationQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAM_ERROR);
        // 查询数据库
        Page<HarperQuestionRelation> harperQuestionRelationPage = harperQuestionRelationService.page(new Page<>(current, size),
                harperQuestionRelationService.getQueryWrapper(harperQuestionRelationQueryRequest));
        // 获取封装类
        return Result.ok(harperQuestionRelationService.getHarperQuestionRelationVOPage(harperQuestionRelationPage, request));
    }

    /**
     * 编辑题库题目关联（给用户使用）
     *
     * @param harperQuestionRelationEditRequest
     * @param request
     * @return
     */
    @PostMapping("/edit")
    public BaseResult<Boolean> editHarperQuestionRelation(@RequestBody HarperQuestionRelationEditRequest harperQuestionRelationEditRequest, HttpServletRequest request) {
        if (harperQuestionRelationEditRequest == null || harperQuestionRelationEditRequest.getId() <= 0) {
            throw new BusiException(ErrorCode.PARAM_ERROR);
        }
        // todo 在此处将实体类和 DTO 进行转换
        HarperQuestionRelation harperQuestionRelation = new HarperQuestionRelation();
        BeanUtils.copyProperties(harperQuestionRelationEditRequest, harperQuestionRelation);
        // 数据校验
        harperQuestionRelationService.validHarperQuestionRelation(harperQuestionRelation, false);
        // 操作数据库
        boolean result = harperQuestionRelationService.updateById(harperQuestionRelation);
        ThrowUtils.throwIf(!result, ErrorCode.OPER_ERROR);
        return Result.ok(true);
    }
}

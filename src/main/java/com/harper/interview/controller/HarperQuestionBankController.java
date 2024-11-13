package com.harper.interview.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harper.interview.common.*;
import com.harper.interview.dto.harperQuestionBank.HarperQuestionBankAddRequest;
import com.harper.interview.dto.harperQuestionBank.HarperQuestionBankEditRequest;
import com.harper.interview.dto.harperQuestionBank.HarperQuestionBankQueryRequest;
import com.harper.interview.dto.harperQuestionBank.HarperQuestionBankUpdateRequest;
import com.harper.interview.entity.HarperQuestionBank;
import com.harper.interview.factory.UserServiceFactory;
import com.harper.interview.service.HarperQuestionBankService;
import com.harper.interview.service.UserService;
import com.harper.interview.vo.HarperQuestionBankVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题库接口
 */
@RestController
@RequestMapping("/harperQuestionBank")
@Slf4j
public class HarperQuestionBankController {

    @Resource
    private HarperQuestionBankService harperQuestionBankService;

    @Resource
    private UserServiceFactory userServiceFactory;

    /**
     * 创建题库
     *
     * @param harperQuestionBankAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResult<Long> addHarperQuestionBank(@RequestBody HarperQuestionBankAddRequest harperQuestionBankAddRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(harperQuestionBankAddRequest == null, ErrorCode.PARAM_ERROR);
        // todo 在此处将实体类和 DTO 进行转换
        HarperQuestionBank harperQuestionBank = new HarperQuestionBank();
        BeanUtils.copyProperties(harperQuestionBankAddRequest, harperQuestionBank);
        // 数据校验
        harperQuestionBankService.validHarperQuestionBank(harperQuestionBank, true);
        // 返回新写入的数据 id
        long newHarperQuestionBankId = harperQuestionBank.getId();
        return Result.ok(newHarperQuestionBankId);
    }

    /**
     * 删除题库
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResult<Boolean> deleteHarperQuestionBank(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusiException(ErrorCode.PARAM_ERROR);
        }
        long id = deleteRequest.getId();
        // 判断是否存在
        HarperQuestionBank oldHarperQuestionBank = harperQuestionBankService.getById(id);
        ThrowUtils.throwIf(oldHarperQuestionBank == null, ErrorCode.NOT_FOUND_ERROR);
        // 操作数据库
        boolean result = harperQuestionBankService.removeById(id);
        ThrowUtils.throwIf(!result, ErrorCode.OPER_ERROR);
        return Result.ok(true);
    }

    /**
     * 更新题库（仅管理员可用）
     *
     * @param harperQuestionBankUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public BaseResult<Boolean> updateHarperQuestionBank(@RequestBody HarperQuestionBankUpdateRequest harperQuestionBankUpdateRequest) {
        if (harperQuestionBankUpdateRequest == null || harperQuestionBankUpdateRequest.getId() <= 0) {
            throw new BusiException(ErrorCode.PARAM_ERROR);
        }
        // todo 在此处将实体类和 DTO 进行转换
        HarperQuestionBank harperQuestionBank = new HarperQuestionBank();
        BeanUtils.copyProperties(harperQuestionBankUpdateRequest, harperQuestionBank);
        // 数据校验
        harperQuestionBankService.validHarperQuestionBank(harperQuestionBank, false);
        // 判断是否存在
        long id = harperQuestionBankUpdateRequest.getId();
        HarperQuestionBank oldHarperQuestionBank = harperQuestionBankService.getById(id);
        ThrowUtils.throwIf(oldHarperQuestionBank == null, ErrorCode.NOT_FOUND_ERROR);
        // 操作数据库
        boolean result = harperQuestionBankService.updateById(harperQuestionBank);
        ThrowUtils.throwIf(!result, ErrorCode.OPER_ERROR);
        return Result.ok(true);
    }

    /**
     * 根据 id 获取题库（封装类）
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResult<HarperQuestionBankVO> getHarperQuestionBankVOById(long id, HttpServletRequest request) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAM_ERROR);
        // 查询数据库
        HarperQuestionBank harperQuestionBank = harperQuestionBankService.getById(id);
        ThrowUtils.throwIf(harperQuestionBank == null, ErrorCode.NOT_FOUND_ERROR);
        // 获取封装类
        return Result.ok(harperQuestionBankService.getHarperQuestionBankVO(harperQuestionBank, request));
    }

    /**
     * 分页获取题库列表（仅管理员可用）
     *
     * @param harperQuestionBankQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResult<Page<HarperQuestionBank>> listHarperQuestionBankByPage(@RequestBody HarperQuestionBankQueryRequest harperQuestionBankQueryRequest) {
        long current = harperQuestionBankQueryRequest.getCurrent();
        long size = harperQuestionBankQueryRequest.getPageSize();
        // 查询数据库
        Page<HarperQuestionBank> harperQuestionBankPage = harperQuestionBankService.page(new Page<>(current, size),
                harperQuestionBankService.getQueryWrapper(harperQuestionBankQueryRequest));
        return Result.ok(harperQuestionBankPage);
    }

    /**
     * 分页获取题库列表（封装类）
     *
     * @param harperQuestionBankQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResult<Page<HarperQuestionBankVO>> listHarperQuestionBankVOByPage(@RequestBody HarperQuestionBankQueryRequest harperQuestionBankQueryRequest,
                                                                                 HttpServletRequest request) {
        long current = harperQuestionBankQueryRequest.getCurrent();
        long size = harperQuestionBankQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAM_ERROR);
        // 查询数据库
        Page<HarperQuestionBank> harperQuestionBankPage = harperQuestionBankService.page(new Page<>(current, size),
                harperQuestionBankService.getQueryWrapper(harperQuestionBankQueryRequest));
        // 获取封装类
        return Result.ok(harperQuestionBankService.getHarperQuestionBankVOPage(harperQuestionBankPage, request));
    }

    /**
     * 分页获取当前登录用户创建的题库列表
     *
     * @param harperQuestionBankQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/my/list/page/vo")
    public BaseResult<Page<HarperQuestionBankVO>> listMyHarperQuestionBankVOByPage(@RequestBody HarperQuestionBankQueryRequest harperQuestionBankQueryRequest,
                                                                                   HttpServletRequest request) {
        ThrowUtils.throwIf(harperQuestionBankQueryRequest == null, ErrorCode.PARAM_ERROR);
        long current = harperQuestionBankQueryRequest.getCurrent();
        long size = harperQuestionBankQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAM_ERROR);
        // 查询数据库
        Page<HarperQuestionBank> harperQuestionBankPage = harperQuestionBankService.page(new Page<>(current, size),
                harperQuestionBankService.getQueryWrapper(harperQuestionBankQueryRequest));
        // 获取封装类
        return Result.ok(harperQuestionBankService.getHarperQuestionBankVOPage(harperQuestionBankPage, request));
    }

    /**
     * 编辑题库（给用户使用）
     *
     * @param harperQuestionBankEditRequest
     * @param request
     * @return
     */
    @PostMapping("/edit")
    public BaseResult<Boolean> editHarperQuestionBank(@RequestBody HarperQuestionBankEditRequest harperQuestionBankEditRequest, HttpServletRequest request) {
        if (harperQuestionBankEditRequest == null || harperQuestionBankEditRequest.getId() <= 0) {
            throw new BusiException(ErrorCode.PARAM_ERROR);
        }
        // todo 在此处将实体类和 DTO 进行转换
        HarperQuestionBank harperQuestionBank = new HarperQuestionBank();
        BeanUtils.copyProperties(harperQuestionBankEditRequest, harperQuestionBank);
        // 数据校验
        harperQuestionBankService.validHarperQuestionBank(harperQuestionBank, false);
        // 判断是否存在
        long id = harperQuestionBankEditRequest.getId();
        HarperQuestionBank oldHarperQuestionBank = harperQuestionBankService.getById(id);
        ThrowUtils.throwIf(oldHarperQuestionBank == null, ErrorCode.NOT_FOUND_ERROR);
        // 操作数据库
        boolean result = harperQuestionBankService.updateById(harperQuestionBank);
        ThrowUtils.throwIf(!result, ErrorCode.OPER_ERROR);
        return Result.ok(true);
    }
}

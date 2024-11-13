package com.harper.interview.controller;

import com.harper.interview.common.BaseResult;
import com.harper.interview.common.Result;
import com.harper.interview.entity.CategoryEntity;
import com.harper.interview.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 目录层级
 * @author: liuhb_mios_ah
 * @date: 2023/12/8 15:56
 * @param:
 * @return:
 **/
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
    @Resource
    private CommonService commonService;

    /**
     * @description: 查询目录结构
     * @author: liuhb_mios_ah
     * @date: 2023/12/8 15:56
     * @param: []
     * @return: com.harper.interview.common.BaseResult<java.util.List < com.harper.interview.entity.CategoryEntity>>
     **/
    @PostMapping("/list/tree")
    public BaseResult<List<CategoryEntity>> list() {
        List<CategoryEntity> entities = commonService.listWithTree();
        return Result.ok(entities);
    }
}

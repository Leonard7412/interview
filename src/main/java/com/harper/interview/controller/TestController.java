package com.harper.interview.controller;

import com.harper.interview.entity.TestEntity;
import com.harper.interview.repository.IExcelRepository;
import com.harper.interview.service.ITestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName：TestController
 * @Author: leo.harper
 * @Date: 2024/12/15 14:49
 * @Description: 必须描述类做什么事情, 实现什么功能
 */
@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class TestController {
    private String filePath = "/doc";
    @Resource
    private IExcelRepository excelService;
    @Resource
    private ITestService testService;


    @PostMapping("/importExcel")
    public void importExcel(@RequestParam MultipartFile file) {
        excelService.importExcel(file, TestEntity.class, 2, testService::saveBatch);
    }


    @PostMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        excelService.exportExcel(testService.list(), TestEntity.class, filePath, response);
    }


}

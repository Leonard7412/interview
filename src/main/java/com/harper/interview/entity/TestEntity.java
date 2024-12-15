package com.harper.interview.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.harper.interview.utils.DateConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName：TestEntity
 * @Author: leo.harper
 * @Date: 2024/12/15 14:44
 * @Description: 必须描述类做什么事情, 实现什么功能
 */
@Data
@TableName("test")
public class TestEntity {

    /**
     * 数字
     */
    @Schema(description = "数字")
    @ExcelProperty("数字")
    private BigDecimal num;


    /**
     * 性别
     */
    @Schema(description = "性别")
    @ExcelProperty("性别")
    private String sex;


    /**
     * 姓名
     */
    @Schema(description = "姓名")
    @ExcelProperty("姓名")
    private String name;


    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @ExcelProperty(value = "创建时间", converter = DateConverter.class)
    private Date bornDate;


}

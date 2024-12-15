package com.harper.interview.utils;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @ClassName：DateConverter
 * @Author: leo.harper
 * @Date: 2024/12/15 14:34
 * @Description: excel日期工具类
 */
public class DateConverter implements Converter<Date> {

    @Override
    public Date convertToJavaData(ReadConverterContext<?> context) throws Exception {
        Class<?> aClass = context.getContentProperty().getField().getType();
        CellDataTypeEnum type = context.getReadCellData().getType();
        String stringValue = context.getReadCellData().getStringValue();
        if (aClass.equals(Date.class) && type.equals(CellDataTypeEnum.STRING) && StringUtils.isBlank(stringValue)) {
            return null;
        }

        return Converter.super.convertToJavaData(context);
    }
}

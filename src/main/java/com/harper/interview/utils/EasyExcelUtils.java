package com.harper.interview.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.interview.utils
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-11-25  11:24
 * @Description: TODO
 * @Version: 1.0
 */
public class EasyExcelUtils {
    public static <T> void generateTemplate(String templateFileName, List<T> headers) {
        ExcelWriterBuilder writerBuilder = EasyExcel.write(templateFileName);
        if (headers.isEmpty()) {
            throw new IllegalArgumentException("Header list is empty");
        }
        // 获取列表元素的类型
        Class<?> headerClass = headers.get(0).getClass();

        // 判断列表元素的类型，并进行相应的处理
        if (headerClass == String.class) {
            writerBuilder.head(convertHead((List<String>) headers)).sheet("template").doWrite(new ArrayList<>());
        } else if (Map.class.isAssignableFrom(headerClass)) {
            // 处理Map类型的表头
            List<String> mapKeys = extractMapKeys(headers);
            writerBuilder.head(convertHead(mapKeys)).sheet("template").doWrite(new ArrayList<>());
        } else {
            // 处理实体类类型的表头
            List<String> entityHeaders = extractFieldNames(headerClass);
            writerBuilder.head(convertHead(entityHeaders)).sheet("template").doWrite(new ArrayList<>());
        }
    }

    private static List<List<String>> convertHead(List<String> headList) {
        List<List<String>> list = new ArrayList<>();
        for (String head : headList) {
            list.add(Lists.newArrayList(head));
        }
        return list;
    }

    // 处理Map的值
    private static <T> List<String> extractMapKeys(List<T> headers) {
        List<String> mapKeys = new ArrayList<>();
        for (T header : headers) {
            if (header instanceof Map) {
                Map<?, ?> map = (Map<?, ?>) header;
                mapKeys.addAll(map.values().stream()
                        .map(Object::toString)
                        .collect(Collectors.toList()));
            }
        }
        return mapKeys;
    }

    // 处理实体类
    private static <T> List<String> extractFieldNames(Class<T> entityClass) {
        List<String> fieldNames = new ArrayList<>();
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }
}

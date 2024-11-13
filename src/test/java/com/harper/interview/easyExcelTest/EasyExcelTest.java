package com.harper.interview.easyExcelTest;

import com.harper.interview.utils.EasyExcelUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.interview.easyExcelTest
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-11-25  11:47
 * @Description: TODO
 * @Version: 1.0
 */
@SpringBootTest
public class EasyExcelTest {

    @Test
    void easyExcelTest() {
        String templateFileName = "template.xlsx";
        // 演示传递 String 类型的列表
        List<String> stringHeaders = new ArrayList<>();
        stringHeaders.add("Name");
        stringHeaders.add("Age");
        EasyExcelUtils.generateTemplate(templateFileName, stringHeaders);
        // 演示传递 Map 类型的列表
        List<Map<String, String>> mapHeaders = new ArrayList<>();
        // 添加map数据，示例中使用了String类型的Map
        Map<String, String> map1 = new HashMap<>();
        map1.put("Key1", "Name");
        map1.put("Key2", "Age");
        mapHeaders.add(map1);
        EasyExcelUtils.generateTemplate(templateFileName, mapHeaders);
        // 演示传递实体类类型的列表
        List<?> entityHeaders = new ArrayList<>();
        // 添加实体类数据
        EasyExcelUtils.generateTemplate(templateFileName, entityHeaders);
    }
}

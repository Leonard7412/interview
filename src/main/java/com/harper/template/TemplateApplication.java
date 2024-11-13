package com.harper.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.template
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-11-24  16:43
 * @Description: TODO
 * @Version: 1.0
 */

@MapperScan("com.harper.template.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
public class TemplateApplication {
    public static void main(String[] args) {
        SpringApplication.run(TemplateApplication.class, args);
    }
}

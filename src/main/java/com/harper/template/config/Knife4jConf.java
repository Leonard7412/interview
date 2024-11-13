package com.harper.template.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.template.config
 * @Author: liuhb_mios_ah
 * @CreateTime: 2023-11-24  17:04
 * @Description: TODO
 * @Version: 1.0
 */
@Configuration
public class Knife4jConf {
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("万能模板接口文档")
                        .description("template-use-desc")
//                        .termsOfServiceUrl("http://www.xx.com/")
//                        .contact("xx@qq.com")
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("default")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.harper.template.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}

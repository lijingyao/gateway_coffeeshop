package com.lijingyao.coffee.gateway.base;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by lijingyao on 2018/8/7 17:10.
 */
@Configuration
@ConditionalOnExpression("'${service.global.version}'!='product'")
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket api() {

        // TODO add your global header info here
        List<Parameter> aParameters = new ArrayList<>();

        return new Docket(DocumentationType.SWAGGER_2).securitySchemes(newArrayList(apiKey())).globalOperationParameters(aParameters)
                .genericModelSubstitutes(ResponseEntity.class).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.lijingyao.coffee.gateway.api")).build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Gateway Platform API").description("CoffeeShop Gateway api schema 描述和定义").termsOfServiceUrl("NO " +
                "terms of service").contact(new Contact("李静瑶", "https://github.com/lijingyao", "lijingyao0909@gmail.com")).version("1.0").build();

    }

    @Bean
    SecurityScheme apiKey() {

        return new ApiKey("your_key_name", "your_pwd", "header");

    }
}

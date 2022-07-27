package com.wipro.productservice;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class ProductServiceApplication {

	/**
	 * @param args
	 * Main Method
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	/**
	 * @return Docket
	 */
	@Bean
	public Docket swaggerConfiguration() {

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.wipro.productservice.controller")).build().apiInfo(apiInfo());
 
	}

	/**
	 * @return ApiInfo
	 */
	private ApiInfo apiInfo() {
		return new ApiInfo("Product Service", "Digirich Ecom Project", "API", "Terms of service",
				new Contact("Digirich' Ecom", "", "abc@email.com"), "License of API", "", Collections.emptyList());
	}
}
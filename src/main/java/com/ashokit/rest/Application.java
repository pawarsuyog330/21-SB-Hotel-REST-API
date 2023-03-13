package com.ashokit.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
public class Application implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:1212")
				.allowedMethods("GET", "POST");
	}
	
	@Bean
	public  Docket docket() {
		
		ApiInfoBuilder  builder=new ApiInfoBuilder();
		builder.title("Hotel REST Api Documentation");
		builder.version("1.1");
		builder.description("This rest api can be used to perform CRUD operations on Hotels");
		builder.license("ASHOKIT SCHOOL");
		builder.licenseUrl("http://ashokitech.in");
		
		ApiInfo  apiInfo= builder.build();
		
		
		return new Docket(DocumentationType.SWAGGER_2)
		           .select()
		           .apis(RequestHandlerSelectors.basePackage("com.ashokit.rest.controller"))
		           .paths(PathSelectors.any())
		           .build()
		           .apiInfo(apiInfo);
	}

}

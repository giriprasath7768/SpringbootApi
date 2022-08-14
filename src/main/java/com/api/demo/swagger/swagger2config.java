package com.api.demo.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class swagger2config {
	
	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.api.demo.Controller"))
				.paths(PathSelectors.regex("/.*")).build().apiInfo(apiEnpointsinfo());
		
	}
	
	
	private ApiInfo apiEnpointsinfo()
	{
		return new ApiInfoBuilder().title("User Management Rest API")
				.description("API deals with the User Informations")
				.contact(new Contact("TrioTendSolutions", "Gp@Triotend.com", "www.triotend.com"))
		        .license("TTS-7.3")
		        .licenseUrl("www.triotend.com")
		        .version("7.3.0")
		        .build();
	}

}

package com.skytala.eCommerce.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
public class SwaggerConfig{
	
	private boolean enableSwagger;
	
	@Bean
	public Docket api() {

		
        if(System.getProperty("sun.java.command").contains("--test")) {
        	enableSwagger = false;
        }else {
        	
        	String configLocation = System.getProperty("user.dir")+"/plugins/eCommerce/config/swagger.conf";
        	File f = new File(configLocation);
        	
        	try {
        		Scanner s = new Scanner(f);
        		
        		if(s.hasNextLine()) {
        			String line = s.nextLine();
        			
        			if(line.split("=")[0].trim().contains("enabled")) {
        				if(line.split("=")[1].trim().contains("true")) {
        					enableSwagger = true;
        				}else {
        					enableSwagger = false;
        				}
        			}
        		}
        		
        		s.close();
        	} catch (FileNotFoundException e) {
        		
        		System.err.println("File "+configLocation+" not found. Enabling Swagger by default");
				enableSwagger = true;
        	}
        	
        }
 
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo())
				.enable(enableSwagger);
	}
	

	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Ofbiz")
            .description("ofbiz service controlling and CRUD")
            .version("0.1 pre Alpha")
            .termsOfServiceUrl("http://terms-of-services.url")
            .license("LICENSE")
            .licenseUrl("http://url-to-license.com")
            .contact(new Contact("Skytala GmbH", "http://www.skytala-gmbh.com/", "info@skytala-gmbh.com"))
            .build();
    }
	
}

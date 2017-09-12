package com.skytala.eCommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebAppConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addRedirectViewController("/null/v2/api-docs", "/v2/api-docs?group=restful-api");
        	registry.addRedirectViewController("/null/swagger-resources/configuration/ui","/swagger-resources/configuration/ui");
        	registry.addRedirectViewController("/null/swagger-resources/configuration/security","/swagger-resources/configuration/security");
        	registry.addRedirectViewController("/null/swagger-resources", "/swagger-resources");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/swagger-ui.html")
	      .addResourceLocations("classpath:/META-INF/resources/");
	 
	    registry.addResourceHandler("/webjars/**")
	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
}

package com.skytala.eCommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@Import({ SecurityConfig.class })
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

	//TODO remove
	@Override
	public void addCorsMappings(CorsRegistry registry){
		registry.addMapping("/**")
				.allowedOrigins(SecurityConfig.CORS_SERVER_URL, "localhost:3000")
				.allowCredentials(true)
				.allowedMethods(HttpMethod.POST.name(),
								HttpMethod.PUT.name(),
								HttpMethod.OPTIONS.name(),
								HttpMethod.GET.name(),
								HttpMethod.DELETE.name());
	}

	@Bean(name = "validator")
	public Validator validator(){
		return new LocalValidatorFactoryBean();
	}


	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource(){
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:derby:ofbiz");
		driverManagerDataSource.setUsername("ofbiz");
		//driverManagerDataSource.setPassword("derby-ofbiz");
		return driverManagerDataSource;
	}



}

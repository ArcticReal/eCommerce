package com.skytala.eCommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;

import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    DataSource dataSource;

    public static final String CORS_SERVER_URL = "http://192.168.49.60:3000";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select USER_LOGIN_ID, CURRENT_PASSWORD, " +
                        "case ENABLED when 'Y' then 'true' else 'false' end " +
                        "from USER_LOGIN " +
                        "where USER_LOGIN_ID=?"
                )
                .authoritiesByUsernameQuery(
                        "select USER_LOGIN_ID, GROUP_ID " +
                        "from USER_LOGIN_SECURITY_GROUP " +
                        "where USER_LOGIN_ID=? " +
                        "and FROM_DATE < CURRENT_TIMESTAMP " +                      //  authorities can be granted for specific time periods
                        "and (THRU_DATE > CURRENT_TIMESTAMP or THRU_DATE IS NULL)"  //  this query will only get authorities from the database
                                                                                    //  if the given time specifications match with the current time
                )
                .passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
				.anyRequest()
					.permitAll()		//TODO: remove!!!
                .antMatchers((HttpMethod)null, "/api/products/**")
                    .permitAll()
                .antMatchers((HttpMethod)null, "/api/service/products/**")
                    .permitAll()
                .antMatchers((HttpMethod)null, "/api/service/productView/**")
                    .permitAll()
                .antMatchers((HttpMethod)null, "/api/register",
                                                           "/api/userDetails/**",
                                                           "/api/account/**")
                    .permitAll()
                .antMatchers((HttpMethod)null, "/api/resendVerificationMail/**")
                    .permitAll()
                .antMatchers((HttpMethod)null, "/api/resources/**")
                    .permitAll()
                .antMatchers((HttpMethod)null, "/api/geo/**")
                    .permitAll()
                .antMatchers(GET, "/api/productPromos/**")
                    .permitAll()
                .antMatchers((HttpMethod)null, "/api/productCategorys/**")
                    .permitAll()
                .antMatchers((HttpMethod)null, "/api/loggedInPerson*")
                    .permitAll()
                .antMatchers((HttpMethod)null, "/api/order/**")
                    .authenticated()
                .antMatchers((HttpMethod)null, "/api/cart/**")
                    .authenticated()
                .antMatchers((HttpMethod)null, "/api/successfulLogin")
                    .authenticated()
                .antMatchers((HttpMethod)null, "/api/loginFailed/**")
                    .permitAll()
                .antMatchers((HttpMethod)null, "/api/queryyy")//TODO: remove!!!
                    .permitAll()
                //.anyRequest()
                //    .denyAll()
                .and()
          .formLogin()
                .loginProcessingUrl("/api/login")
                    .permitAll()
                .loginPage("/api/loginNeeded")
                    .permitAll()
                .defaultSuccessUrl("/api/successfulLogin", true)
                .failureHandler(failureHandler())
                .and()
          .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessUrl("/api/successfulLogout")
                    .permitAll()
                .and()
          .csrf()
                .disable()
          .cors();//TODO: remove on server join
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        return encoder;
    }

    @Bean
    AuthenticationFailureHandler failureHandler(){
        return new CustomAuthFailureHandler();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){//TODO: remove on server join

        CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", CORS_SERVER_URL));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		configuration.addAllowedHeader("Content-Type");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
    }

}

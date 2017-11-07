package com.skytala.eCommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("test").password("test1").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
                .anyRequest().authenticated()
                .antMatchers("/api/login*").anonymous()
                .and()
          .formLogin()
                .loginPage("/api/login.html")
                .permitAll()
                .defaultSuccessUrl("/homepage.html")
                .failureUrl("/api/login.html?error=true")
                .defaultSuccessUrl("/api/products/productList", false)
                .loginProcessingUrl("/api/login")
                .permitAll()
                .and()
          .logout()
                .logoutSuccessUrl("/api//login.html")
                .and()
          .csrf()
                .disable();
    }

}

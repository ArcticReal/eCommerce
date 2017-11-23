package com.skytala.eCommerce.config;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthFailureHandler implements AuthenticationFailureHandler{

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        if(exception.getClass().equals(DisabledException.class))
            response.sendRedirect("/eCommerce/api/loginFailed/userIsDisabled");
        else
            response.sendRedirect("/eCommerce/api/loginFailed/badCredentials");
    }
}

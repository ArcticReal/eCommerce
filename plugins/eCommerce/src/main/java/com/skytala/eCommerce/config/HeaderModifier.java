package com.skytala.eCommerce.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class HeaderModifier implements ResponseBodyAdvice{

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        // allow content loading by cross origin javascript calls
        response.getHeaders().add("Access-Control-Allow-Origin",request.getHeaders().getOrigin()); //TODO allow only specific pages to load contents cross origin
        response.getHeaders().add("Access-Control-Allow-Credentials", "true");
        return body;
    }
}

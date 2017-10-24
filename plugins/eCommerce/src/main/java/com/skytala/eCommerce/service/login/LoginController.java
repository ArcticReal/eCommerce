package com.skytala.eCommerce.service.login;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @RequestMapping("/login")
    public void doLoginForwarded(HttpServletRequest request, HttpServletResponse response) throws Exception{

        response.setHeader("Access-Control-Allow-Origin", "192.*");//TODO look for origins to allow access from
        RequestDispatcher rd = request.getRequestDispatcher("/control/login");
        rd.forward(request, response);
    }



}

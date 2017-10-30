package com.skytala.eCommerce.service;

import com.skytala.eCommerce.domain.party.relations.person.dto.LoggedInPersonNameDTO;
import com.skytala.eCommerce.domain.party.relations.person.mapper.PersonMapper;
import org.apache.ofbiz.entity.GenericValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.badRequest;
import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.successful;
import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.unauthorized;


@RestController
public class LoginController {

    @RequestMapping("/login")
    public void doLoginForwarded(HttpServletRequest request, HttpServletResponse response) throws Exception{

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");//TODO look for origins to allow access from
        response.setHeader("Access-Control-Allow-Credentials", "true");
        RequestDispatcher rd = request.getRequestDispatcher("/control/login");
        rd.forward(request, response);
    }

    @RequestMapping("/logout")
    public void doLogoutForwarded(HttpServletRequest request, HttpServletResponse response) throws Exception{

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");//TODO look for origins to allow access from
        response.setHeader("Access-Control-Allow-Credentials", "true");
        RequestDispatcher rd = request.getRequestDispatcher("/control/logout");
        rd.forward(request, response);
    }

    @RequestMapping("/loggedInPerson")
    public ResponseEntity getUserLoginName(HttpSession session){

        return successful(session.getAttribute("person"));

    }

    @RequestMapping("/loggedInPersonName")
    public ResponseEntity<LoggedInPersonNameDTO> getLoggedInPersonName(HttpSession session){
        GenericValue person = (GenericValue)session.getAttribute("person");

        if(person != null){
            LoggedInPersonNameDTO dto = new LoggedInPersonNameDTO(PersonMapper.map(person));
            return successful(dto);
        }

        return unauthorized();
    }

}

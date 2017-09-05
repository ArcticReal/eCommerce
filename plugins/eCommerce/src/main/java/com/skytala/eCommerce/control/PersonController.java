package com.skytala.eCommerce.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skytala.eCommerce.command.AddPerson;
import com.skytala.eCommerce.command.AddUserLogin;
import com.skytala.eCommerce.entity.Person;
import com.skytala.eCommerce.entity.PersonMapper;
import com.skytala.eCommerce.entity.Product;
import com.skytala.eCommerce.entity.UserLogin;
import com.skytala.eCommerce.entity.UserLoginMapper;
import com.skytala.eCommerce.event.PersonAdded;
import com.skytala.eCommerce.event.UserLoginAdded;

@RestController
@RequestMapping("api/person")
public class PersonController {
	private static int requestTicketId = 0;
	private static Map<Integer, List<Product>> queryReturnVal = new HashMap<>();
	private static Map<Integer, Boolean> commandReturnVal = new HashMap<>();

	@RequestMapping(method = RequestMethod.POST, value = { "/create", "/insert",
			"add" }, consumes = "application/x-www-form-urlencoded")
	public boolean createPerson(@RequestParam Map<String, String> allRequestParams) {
		Person person = new Person();
		
		try{
			person.setPartyId("IstEgal");
			person = PersonMapper.mapstrstr(allRequestParams);
				
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		this.createPerson(person);
		this.createLogin(allRequestParams);
	
//		this.createLogin(login);
		return true;
	}
	
	public boolean createPerson(Person person){
		
		AddPerson com = new AddPerson(person);
		int usedTicketId;
		
		
		synchronized(PersonController.class){
			usedTicketId = requestTicketId;
			requestTicketId++;
		}
		Broker.instance().subscribe(PersonAdded.class, event -> sendPersonChangedMessage(((PersonAdded) event).isSuccess(), usedTicketId));
		
		try {
			Scheduler.instance().schedule(com).executeNext();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
		
		return true;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/login/create", consumes = "application/x-www-form-urlencoded")
	public boolean createLogin(Map<String,String> params){
		UserLogin user = new UserLogin();
		
		try {
			int usedTicketId;
		
			String partyId = params.get("partyId");
			
			user = UserLoginMapper.mapstrstr(params);
			AddUserLogin login = new AddUserLogin(partyId,user);
			
			
			synchronized(PersonController.class){
				usedTicketId = requestTicketId;
				requestTicketId++;
			}
			Broker.instance().subscribe(UserLoginAdded.class, event -> sendUserLoginChangedMessage(((UserLoginAdded)event).isSuccess(), usedTicketId));
			
			Scheduler.instance().schedule(login).executeNext();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return true;
	}
	
	
	
	public void sendPersonChangedMessage(boolean success, int id) {
		commandReturnVal.put(id, success);
	}
		public void sendUserLoginChangedMessage(boolean success, int id) {
			commandReturnVal.put(id, success);
		}

}

package com.skytala.eCommerce.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.condition.EntityCondition;
import org.apache.ofbiz.entity.util.EntityListIterator;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.Party;
import com.skytala.eCommerce.entity.PartyMapper;
import com.skytala.eCommerce.entity.UserLogin;
import com.skytala.eCommerce.entity.UserLoginMapper;
import com.skytala.eCommerce.event.PersonAdded;

public class AddUserLogin implements Command{
	
	private String partyId;
	private UserLogin login;
	
	public AddUserLogin(String partyId, UserLogin login){
		this.partyId = partyId;
		this.login = login;
	}
	
	@Override
	public void execute(){
		Delegator delegator = DelegatorFactory.getDelegator("default");
		
		
		boolean success;
		
		try {
		
			
		
		
			
			 delegator.create("UserLogin", UserLoginMapper.map(login));
		
//			buf.setUserLoginId(login.getUserLoginId());
			
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			success =false;
		}
		
		Broker.instance().publish(new PersonAdded(success));
	}
}

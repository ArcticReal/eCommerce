package com.skytala.eCommerce.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.Party;
import com.skytala.eCommerce.entity.PartyMapper;
import com.skytala.eCommerce.entity.Person;
import com.skytala.eCommerce.entity.PersonMapper;
import com.skytala.eCommerce.event.PersonAdded;

public class AddPerson implements Command {
	private Person personToBeAdded;
	
	public AddPerson(Person personToBeAdded){
		this.personToBeAdded = personToBeAdded;
	}
	
	@Override
	public void execute(){
		Delegator delegator = DelegatorFactory.getDelegator("default");
		Party part = new Party();
		//part.setPartyId(delegator.getNextSeqId("Party"));
		part.setPartyId(personToBeAdded.getPartyId());
		
		
		boolean success;
		
		try {
			personToBeAdded.setPartyId(part.getPartyId());
			
			part.setPartyTypeId("PERSON");
			part.setStatusId("PARTY_ENABLED");
			
			
			GenericValue myval = delegator.makeValue("Party",PartyMapper.map(part));
			GenericValue val = delegator.makeValue("Person",PersonMapper.map(personToBeAdded));
			
			delegator.create(myval);
			delegator.create(val);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		Broker.instance().publish(new PersonAdded(success));
		
	}
	

}

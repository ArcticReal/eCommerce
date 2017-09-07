package com.skytala.eCommerce.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.Person;
import com.skytala.eCommerce.event.PersonAdded;

public class AddPerson implements Command {

	private Person elementToBeAdded;

	public AddPerson(Person elementToBeAdded) {
		this.elementToBeAdded = elementToBeAdded;
	}

	@Override
	public void execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success;
		try {
//			elementToBeAdded.setPersonId(delegator.getNextSeqId("Person"));
			GenericValue newValue = delegator.makeValue("Person", elementToBeAdded.mapAttributeField());
			delegator.create(newValue);
			success = true;
		} catch (GenericEntityException e) {
			e.printStackTrace();
			success = false;
		}

		Broker.instance().publish(new PersonAdded(success));
	}
}

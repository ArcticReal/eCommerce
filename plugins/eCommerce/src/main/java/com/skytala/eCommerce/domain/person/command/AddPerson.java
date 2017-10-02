package com.skytala.eCommerce.domain.person.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.person.event.PersonAdded;
import com.skytala.eCommerce.domain.person.mappper.PersonMapper;
import com.skytala.eCommerce.domain.person.model.Person;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class AddPerson extends Command {

	private Person elementToBeAdded;

	public AddPerson(Person elementToBeAdded) {
		this.elementToBeAdded = elementToBeAdded;
	}

	@Override
	public Event execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success;
		Person addedPerson = null;
		try {
//			elementToBeAdded.setPersonId(delegator.getNextSeqId("Person"));
			GenericValue newValue = delegator.makeValue("Person", elementToBeAdded.mapAttributeField());
			addedPerson = PersonMapper.map(delegator.create(newValue));
			success = true;
		} catch (GenericEntityException e) {
			e.printStackTrace();
			success = false;
		}

		Event resultingEvent = new PersonAdded(addedPerson, success);
		Broker.instance().publish(resultingEvent);
		return resultingEvent;
	}
}

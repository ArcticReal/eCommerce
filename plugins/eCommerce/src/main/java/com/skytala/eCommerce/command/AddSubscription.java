package com.skytala.eCommerce.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.Subscription;
import com.skytala.eCommerce.event.SubscriptionAdded;

public class AddSubscription implements Command {

	private Subscription elementToBeAdded;

	public AddSubscription(Subscription elementToBeAdded) {
		this.elementToBeAdded = elementToBeAdded;
	}

	@Override
	public void execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success;
		try {
			elementToBeAdded.setSubscriptionId(delegator.getNextSeqId("Subscription"));
			GenericValue newValue = delegator.makeValue("Subscription", elementToBeAdded.mapAttributeField());
			delegator.create(newValue);
			success = true;
		} catch (GenericEntityException e) {
			System.err.println(e.getMessage());
			success = false;
		}

		Broker.instance().publish(new SubscriptionAdded(elementToBeAdded, success));
	}
}

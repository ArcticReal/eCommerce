package com.skytala.eCommerce.domain.subscription.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.subscription.event.SubscriptionAdded;
import com.skytala.eCommerce.domain.subscription.model.Subscription;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class AddSubscription extends Command {

	private Subscription elementToBeAdded;

	public AddSubscription(Subscription elementToBeAdded) {
		this.elementToBeAdded = elementToBeAdded;
	}

	@Override
	public Event execute() {

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
		return null;
	}
}

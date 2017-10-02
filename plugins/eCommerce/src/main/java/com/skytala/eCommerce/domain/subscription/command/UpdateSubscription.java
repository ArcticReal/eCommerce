package com.skytala.eCommerce.domain.subscription.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.subscription.event.SubscriptionUpdated;
import com.skytala.eCommerce.domain.subscription.model.Subscription;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSubscription extends Command {

	private Subscription elementToBeUpdated;

	public UpdateSubscription(Subscription elementToBeUpdated) {
		this.elementToBeUpdated = elementToBeUpdated;
	}

	public Subscription getElementToBeUpdated() {
		return elementToBeUpdated;
	}

	public void setElementToBeUpdated(Subscription elementToBeUpdated) {
		this.elementToBeUpdated = elementToBeUpdated;
	}

	@Override
	public Event execute() throws RecordNotFoundException {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success;
		try {
			GenericValue newValue = delegator.makeValue("Subscription", elementToBeUpdated.mapAttributeField());
			delegator.store(newValue);
			if (delegator.store(newValue) == 0) {
				throw new RecordNotFoundException(Subscription.class);
			}
			success = true;
		} catch (GenericEntityException e) {
			System.err.println(e.getMessage());
			success = false;
		}
		Broker.instance().publish(new SubscriptionUpdated(success));
		return null;
	}
}

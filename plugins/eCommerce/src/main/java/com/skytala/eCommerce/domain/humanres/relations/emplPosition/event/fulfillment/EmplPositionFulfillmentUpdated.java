package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.fulfillment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.fulfillment.EmplPositionFulfillment;
public class EmplPositionFulfillmentUpdated implements Event{

	private boolean success;

	public EmplPositionFulfillmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

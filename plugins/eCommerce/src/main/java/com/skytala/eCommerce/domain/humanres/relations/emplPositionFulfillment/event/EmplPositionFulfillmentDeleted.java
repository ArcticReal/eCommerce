package com.skytala.eCommerce.domain.humanres.relations.emplPositionFulfillment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPositionFulfillment.model.EmplPositionFulfillment;
public class EmplPositionFulfillmentDeleted implements Event{

	private boolean success;

	public EmplPositionFulfillmentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

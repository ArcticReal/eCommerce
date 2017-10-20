package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.fulfillment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.fulfillment.EmplPositionFulfillment;
public class EmplPositionFulfillmentAdded implements Event{

	private EmplPositionFulfillment addedEmplPositionFulfillment;
	private boolean success;

	public EmplPositionFulfillmentAdded(EmplPositionFulfillment addedEmplPositionFulfillment, boolean success){
		this.addedEmplPositionFulfillment = addedEmplPositionFulfillment;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public EmplPositionFulfillment getAddedEmplPositionFulfillment() {
		return addedEmplPositionFulfillment;
	}

}

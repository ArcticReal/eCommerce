package com.skytala.eCommerce.domain.order.relations.workRequirementFulfillment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.workRequirementFulfillment.model.WorkRequirementFulfillment;
public class WorkRequirementFulfillmentUpdated implements Event{

	private boolean success;

	public WorkRequirementFulfillmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

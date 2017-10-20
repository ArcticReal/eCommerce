package com.skytala.eCommerce.domain.order.relations.requirement.event.workFulfillment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.workFulfillment.WorkRequirementFulfillment;
public class WorkRequirementFulfillmentUpdated implements Event{

	private boolean success;

	public WorkRequirementFulfillmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

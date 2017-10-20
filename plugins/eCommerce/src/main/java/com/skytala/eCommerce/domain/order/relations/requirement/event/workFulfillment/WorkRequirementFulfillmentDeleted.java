package com.skytala.eCommerce.domain.order.relations.requirement.event.workFulfillment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.workFulfillment.WorkRequirementFulfillment;
public class WorkRequirementFulfillmentDeleted implements Event{

	private boolean success;

	public WorkRequirementFulfillmentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

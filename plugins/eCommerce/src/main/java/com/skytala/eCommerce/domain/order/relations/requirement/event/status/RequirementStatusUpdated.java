package com.skytala.eCommerce.domain.order.relations.requirement.event.status;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.status.RequirementStatus;
public class RequirementStatusUpdated implements Event{

	private boolean success;

	public RequirementStatusUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
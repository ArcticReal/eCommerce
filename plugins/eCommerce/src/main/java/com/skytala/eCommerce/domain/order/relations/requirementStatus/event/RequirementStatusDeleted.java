package com.skytala.eCommerce.domain.order.relations.requirementStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirementStatus.model.RequirementStatus;
public class RequirementStatusDeleted implements Event{

	private boolean success;

	public RequirementStatusDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

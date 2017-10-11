package com.skytala.eCommerce.domain.order.relations.requirementType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirementType.model.RequirementType;
public class RequirementTypeDeleted implements Event{

	private boolean success;

	public RequirementTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

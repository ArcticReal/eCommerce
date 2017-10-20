package com.skytala.eCommerce.domain.order.relations.requirement.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.type.RequirementType;
public class RequirementTypeUpdated implements Event{

	private boolean success;

	public RequirementTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

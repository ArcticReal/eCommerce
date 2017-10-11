package com.skytala.eCommerce.domain.order.relations.requirementAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirementAttribute.model.RequirementAttribute;
public class RequirementAttributeUpdated implements Event{

	private boolean success;

	public RequirementAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

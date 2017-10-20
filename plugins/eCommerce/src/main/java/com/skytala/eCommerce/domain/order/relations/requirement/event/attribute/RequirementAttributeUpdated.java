package com.skytala.eCommerce.domain.order.relations.requirement.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.attribute.RequirementAttribute;
public class RequirementAttributeUpdated implements Event{

	private boolean success;

	public RequirementAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.order.relations.requirementAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirementAttribute.model.RequirementAttribute;
public class RequirementAttributeDeleted implements Event{

	private boolean success;

	public RequirementAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

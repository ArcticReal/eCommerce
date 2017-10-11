package com.skytala.eCommerce.domain.order.relations.requirementRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirementRole.model.RequirementRole;
public class RequirementRoleUpdated implements Event{

	private boolean success;

	public RequirementRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

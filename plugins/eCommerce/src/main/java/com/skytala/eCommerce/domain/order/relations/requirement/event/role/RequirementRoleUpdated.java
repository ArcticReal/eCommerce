package com.skytala.eCommerce.domain.order.relations.requirement.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.role.RequirementRole;
public class RequirementRoleUpdated implements Event{

	private boolean success;

	public RequirementRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

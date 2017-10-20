package com.skytala.eCommerce.domain.order.relations.requirement.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.role.RequirementRole;
public class RequirementRoleDeleted implements Event{

	private boolean success;

	public RequirementRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

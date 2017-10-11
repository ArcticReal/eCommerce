package com.skytala.eCommerce.domain.order.relations.requirementRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirementRole.model.RequirementRole;
public class RequirementRoleAdded implements Event{

	private RequirementRole addedRequirementRole;
	private boolean success;

	public RequirementRoleAdded(RequirementRole addedRequirementRole, boolean success){
		this.addedRequirementRole = addedRequirementRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public RequirementRole getAddedRequirementRole() {
		return addedRequirementRole;
	}

}

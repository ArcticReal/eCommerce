package com.skytala.eCommerce.domain.order.relations.requirementRole.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirementRole.model.RequirementRole;
public class RequirementRoleFound implements Event{

	private List<RequirementRole> requirementRoles;

	public RequirementRoleFound(List<RequirementRole> requirementRoles) {
		this.requirementRoles = requirementRoles;
	}

	public List<RequirementRole> getRequirementRoles()	{
		return requirementRoles;
	}

}

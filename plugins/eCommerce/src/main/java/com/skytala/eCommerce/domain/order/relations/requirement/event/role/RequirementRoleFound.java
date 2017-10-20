package com.skytala.eCommerce.domain.order.relations.requirement.event.role;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.role.RequirementRole;
public class RequirementRoleFound implements Event{

	private List<RequirementRole> requirementRoles;

	public RequirementRoleFound(List<RequirementRole> requirementRoles) {
		this.requirementRoles = requirementRoles;
	}

	public List<RequirementRole> getRequirementRoles()	{
		return requirementRoles;
	}

}

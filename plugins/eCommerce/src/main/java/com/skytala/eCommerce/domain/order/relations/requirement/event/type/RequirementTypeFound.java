package com.skytala.eCommerce.domain.order.relations.requirement.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.type.RequirementType;
public class RequirementTypeFound implements Event{

	private List<RequirementType> requirementTypes;

	public RequirementTypeFound(List<RequirementType> requirementTypes) {
		this.requirementTypes = requirementTypes;
	}

	public List<RequirementType> getRequirementTypes()	{
		return requirementTypes;
	}

}

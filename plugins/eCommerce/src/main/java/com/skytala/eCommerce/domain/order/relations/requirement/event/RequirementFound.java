package com.skytala.eCommerce.domain.order.relations.requirement.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.Requirement;
public class RequirementFound implements Event{

	private List<Requirement> requirements;

	public RequirementFound(List<Requirement> requirements) {
		this.requirements = requirements;
	}

	public List<Requirement> getRequirements()	{
		return requirements;
	}

}

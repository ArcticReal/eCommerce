package com.skytala.eCommerce.domain.order.relations.requirement.event.status;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.status.RequirementStatus;
public class RequirementStatusFound implements Event{

	private List<RequirementStatus> requirementStatuss;

	public RequirementStatusFound(List<RequirementStatus> requirementStatuss) {
		this.requirementStatuss = requirementStatuss;
	}

	public List<RequirementStatus> getRequirementStatuss()	{
		return requirementStatuss;
	}

}

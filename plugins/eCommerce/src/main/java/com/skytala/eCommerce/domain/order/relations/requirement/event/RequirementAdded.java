package com.skytala.eCommerce.domain.order.relations.requirement.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.Requirement;
public class RequirementAdded implements Event{

	private Requirement addedRequirement;
	private boolean success;

	public RequirementAdded(Requirement addedRequirement, boolean success){
		this.addedRequirement = addedRequirement;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Requirement getAddedRequirement() {
		return addedRequirement;
	}

}

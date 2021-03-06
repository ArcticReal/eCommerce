package com.skytala.eCommerce.domain.order.relations.requirement.event.status;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.status.RequirementStatus;
public class RequirementStatusAdded implements Event{

	private RequirementStatus addedRequirementStatus;
	private boolean success;

	public RequirementStatusAdded(RequirementStatus addedRequirementStatus, boolean success){
		this.addedRequirementStatus = addedRequirementStatus;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public RequirementStatus getAddedRequirementStatus() {
		return addedRequirementStatus;
	}

}

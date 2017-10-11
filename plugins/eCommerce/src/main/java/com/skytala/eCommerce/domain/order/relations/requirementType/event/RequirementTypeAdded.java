package com.skytala.eCommerce.domain.order.relations.requirementType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirementType.model.RequirementType;
public class RequirementTypeAdded implements Event{

	private RequirementType addedRequirementType;
	private boolean success;

	public RequirementTypeAdded(RequirementType addedRequirementType, boolean success){
		this.addedRequirementType = addedRequirementType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public RequirementType getAddedRequirementType() {
		return addedRequirementType;
	}

}

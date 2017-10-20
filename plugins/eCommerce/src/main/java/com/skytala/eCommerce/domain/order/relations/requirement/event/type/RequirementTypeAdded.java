package com.skytala.eCommerce.domain.order.relations.requirement.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.type.RequirementType;
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

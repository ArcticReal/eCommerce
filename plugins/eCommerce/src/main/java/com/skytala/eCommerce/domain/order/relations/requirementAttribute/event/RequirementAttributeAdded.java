package com.skytala.eCommerce.domain.order.relations.requirementAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirementAttribute.model.RequirementAttribute;
public class RequirementAttributeAdded implements Event{

	private RequirementAttribute addedRequirementAttribute;
	private boolean success;

	public RequirementAttributeAdded(RequirementAttribute addedRequirementAttribute, boolean success){
		this.addedRequirementAttribute = addedRequirementAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public RequirementAttribute getAddedRequirementAttribute() {
		return addedRequirementAttribute;
	}

}

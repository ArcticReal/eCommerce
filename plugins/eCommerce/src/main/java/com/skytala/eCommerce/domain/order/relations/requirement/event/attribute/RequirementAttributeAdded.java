package com.skytala.eCommerce.domain.order.relations.requirement.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.attribute.RequirementAttribute;
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

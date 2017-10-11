package com.skytala.eCommerce.domain.order.relations.requirementTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirementTypeAttr.model.RequirementTypeAttr;
public class RequirementTypeAttrAdded implements Event{

	private RequirementTypeAttr addedRequirementTypeAttr;
	private boolean success;

	public RequirementTypeAttrAdded(RequirementTypeAttr addedRequirementTypeAttr, boolean success){
		this.addedRequirementTypeAttr = addedRequirementTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public RequirementTypeAttr getAddedRequirementTypeAttr() {
		return addedRequirementTypeAttr;
	}

}

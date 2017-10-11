package com.skytala.eCommerce.domain.order.relations.requirementTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirementTypeAttr.model.RequirementTypeAttr;
public class RequirementTypeAttrDeleted implements Event{

	private boolean success;

	public RequirementTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

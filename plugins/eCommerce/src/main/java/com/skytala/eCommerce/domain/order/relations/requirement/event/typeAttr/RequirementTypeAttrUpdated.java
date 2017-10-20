package com.skytala.eCommerce.domain.order.relations.requirement.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.typeAttr.RequirementTypeAttr;
public class RequirementTypeAttrUpdated implements Event{

	private boolean success;

	public RequirementTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.order.relations.requirementCustRequest.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirementCustRequest.model.RequirementCustRequest;
public class RequirementCustRequestUpdated implements Event{

	private boolean success;

	public RequirementCustRequestUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

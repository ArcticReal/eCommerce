package com.skytala.eCommerce.domain.order.relations.custRequest.event.requirement;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.requirement.RequirementCustRequest;
public class RequirementCustRequestUpdated implements Event{

	private boolean success;

	public RequirementCustRequestUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

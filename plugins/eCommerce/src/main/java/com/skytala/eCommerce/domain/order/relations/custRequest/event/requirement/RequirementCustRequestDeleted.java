package com.skytala.eCommerce.domain.order.relations.custRequest.event.requirement;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.custRequest.model.requirement.RequirementCustRequest;
public class RequirementCustRequestDeleted implements Event{

	private boolean success;

	public RequirementCustRequestDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

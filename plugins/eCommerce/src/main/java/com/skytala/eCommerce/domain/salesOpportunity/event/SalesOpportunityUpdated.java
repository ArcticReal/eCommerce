package com.skytala.eCommerce.domain.salesOpportunity.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.salesOpportunity.model.SalesOpportunity;
public class SalesOpportunityUpdated implements Event{

	private boolean success;

	public SalesOpportunityUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

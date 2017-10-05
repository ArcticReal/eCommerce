package com.skytala.eCommerce.domain.salesOpportunityHistory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.salesOpportunityHistory.model.SalesOpportunityHistory;
public class SalesOpportunityHistoryUpdated implements Event{

	private boolean success;

	public SalesOpportunityHistoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

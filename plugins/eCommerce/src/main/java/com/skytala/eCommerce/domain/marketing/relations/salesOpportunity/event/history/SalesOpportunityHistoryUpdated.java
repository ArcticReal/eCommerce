package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.history;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.history.SalesOpportunityHistory;
public class SalesOpportunityHistoryUpdated implements Event{

	private boolean success;

	public SalesOpportunityHistoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

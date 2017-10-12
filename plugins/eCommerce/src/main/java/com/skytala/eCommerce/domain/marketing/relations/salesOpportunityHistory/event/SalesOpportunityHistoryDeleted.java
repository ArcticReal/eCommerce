package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityHistory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityHistory.model.SalesOpportunityHistory;
public class SalesOpportunityHistoryDeleted implements Event{

	private boolean success;

	public SalesOpportunityHistoryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

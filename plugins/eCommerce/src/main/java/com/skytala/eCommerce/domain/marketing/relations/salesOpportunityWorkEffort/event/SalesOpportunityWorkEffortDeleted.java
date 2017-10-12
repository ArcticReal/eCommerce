package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityWorkEffort.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityWorkEffort.model.SalesOpportunityWorkEffort;
public class SalesOpportunityWorkEffortDeleted implements Event{

	private boolean success;

	public SalesOpportunityWorkEffortDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

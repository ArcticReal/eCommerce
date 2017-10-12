package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityCompetitor.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityCompetitor.model.SalesOpportunityCompetitor;
public class SalesOpportunityCompetitorUpdated implements Event{

	private boolean success;

	public SalesOpportunityCompetitorUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

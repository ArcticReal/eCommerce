package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.competitor;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.competitor.SalesOpportunityCompetitor;
public class SalesOpportunityCompetitorDeleted implements Event{

	private boolean success;

	public SalesOpportunityCompetitorDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

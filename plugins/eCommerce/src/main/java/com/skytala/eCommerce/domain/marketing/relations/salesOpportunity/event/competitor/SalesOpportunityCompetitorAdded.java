package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.competitor;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.competitor.SalesOpportunityCompetitor;
public class SalesOpportunityCompetitorAdded implements Event{

	private SalesOpportunityCompetitor addedSalesOpportunityCompetitor;
	private boolean success;

	public SalesOpportunityCompetitorAdded(SalesOpportunityCompetitor addedSalesOpportunityCompetitor, boolean success){
		this.addedSalesOpportunityCompetitor = addedSalesOpportunityCompetitor;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SalesOpportunityCompetitor getAddedSalesOpportunityCompetitor() {
		return addedSalesOpportunityCompetitor;
	}

}

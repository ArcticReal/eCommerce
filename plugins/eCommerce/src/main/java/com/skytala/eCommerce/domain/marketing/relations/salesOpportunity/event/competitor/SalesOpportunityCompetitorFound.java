package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.competitor;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.competitor.SalesOpportunityCompetitor;
public class SalesOpportunityCompetitorFound implements Event{

	private List<SalesOpportunityCompetitor> salesOpportunityCompetitors;

	public SalesOpportunityCompetitorFound(List<SalesOpportunityCompetitor> salesOpportunityCompetitors) {
		this.salesOpportunityCompetitors = salesOpportunityCompetitors;
	}

	public List<SalesOpportunityCompetitor> getSalesOpportunityCompetitors()	{
		return salesOpportunityCompetitors;
	}

}

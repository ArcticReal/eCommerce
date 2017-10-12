package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityCompetitor.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityCompetitor.model.SalesOpportunityCompetitor;
public class SalesOpportunityCompetitorFound implements Event{

	private List<SalesOpportunityCompetitor> salesOpportunityCompetitors;

	public SalesOpportunityCompetitorFound(List<SalesOpportunityCompetitor> salesOpportunityCompetitors) {
		this.salesOpportunityCompetitors = salesOpportunityCompetitors;
	}

	public List<SalesOpportunityCompetitor> getSalesOpportunityCompetitors()	{
		return salesOpportunityCompetitors;
	}

}

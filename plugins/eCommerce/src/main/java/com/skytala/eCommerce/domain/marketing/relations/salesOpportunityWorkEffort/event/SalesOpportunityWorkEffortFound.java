package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityWorkEffort.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityWorkEffort.model.SalesOpportunityWorkEffort;
public class SalesOpportunityWorkEffortFound implements Event{

	private List<SalesOpportunityWorkEffort> salesOpportunityWorkEfforts;

	public SalesOpportunityWorkEffortFound(List<SalesOpportunityWorkEffort> salesOpportunityWorkEfforts) {
		this.salesOpportunityWorkEfforts = salesOpportunityWorkEfforts;
	}

	public List<SalesOpportunityWorkEffort> getSalesOpportunityWorkEfforts()	{
		return salesOpportunityWorkEfforts;
	}

}

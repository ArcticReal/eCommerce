package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.SalesOpportunity;
public class SalesOpportunityFound implements Event{

	private List<SalesOpportunity> salesOpportunitys;

	public SalesOpportunityFound(List<SalesOpportunity> salesOpportunitys) {
		this.salesOpportunitys = salesOpportunitys;
	}

	public List<SalesOpportunity> getSalesOpportunitys()	{
		return salesOpportunitys;
	}

}

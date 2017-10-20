package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.history;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.history.SalesOpportunityHistory;
public class SalesOpportunityHistoryFound implements Event{

	private List<SalesOpportunityHistory> salesOpportunityHistorys;

	public SalesOpportunityHistoryFound(List<SalesOpportunityHistory> salesOpportunityHistorys) {
		this.salesOpportunityHistorys = salesOpportunityHistorys;
	}

	public List<SalesOpportunityHistory> getSalesOpportunityHistorys()	{
		return salesOpportunityHistorys;
	}

}

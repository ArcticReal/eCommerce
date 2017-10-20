package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.history;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.history.SalesOpportunityHistory;
public class SalesOpportunityHistoryAdded implements Event{

	private SalesOpportunityHistory addedSalesOpportunityHistory;
	private boolean success;

	public SalesOpportunityHistoryAdded(SalesOpportunityHistory addedSalesOpportunityHistory, boolean success){
		this.addedSalesOpportunityHistory = addedSalesOpportunityHistory;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SalesOpportunityHistory getAddedSalesOpportunityHistory() {
		return addedSalesOpportunityHistory;
	}

}

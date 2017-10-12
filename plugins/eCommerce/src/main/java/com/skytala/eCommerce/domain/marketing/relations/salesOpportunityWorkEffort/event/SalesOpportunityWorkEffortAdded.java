package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityWorkEffort.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityWorkEffort.model.SalesOpportunityWorkEffort;
public class SalesOpportunityWorkEffortAdded implements Event{

	private SalesOpportunityWorkEffort addedSalesOpportunityWorkEffort;
	private boolean success;

	public SalesOpportunityWorkEffortAdded(SalesOpportunityWorkEffort addedSalesOpportunityWorkEffort, boolean success){
		this.addedSalesOpportunityWorkEffort = addedSalesOpportunityWorkEffort;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SalesOpportunityWorkEffort getAddedSalesOpportunityWorkEffort() {
		return addedSalesOpportunityWorkEffort;
	}

}

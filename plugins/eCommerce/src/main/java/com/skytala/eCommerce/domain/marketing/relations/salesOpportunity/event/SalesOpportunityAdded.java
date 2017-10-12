package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.SalesOpportunity;
public class SalesOpportunityAdded implements Event{

	private SalesOpportunity addedSalesOpportunity;
	private boolean success;

	public SalesOpportunityAdded(SalesOpportunity addedSalesOpportunity, boolean success){
		this.addedSalesOpportunity = addedSalesOpportunity;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SalesOpportunity getAddedSalesOpportunity() {
		return addedSalesOpportunity;
	}

}

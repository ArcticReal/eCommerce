package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityTrckCode.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityTrckCode.model.SalesOpportunityTrckCode;
public class SalesOpportunityTrckCodeAdded implements Event{

	private SalesOpportunityTrckCode addedSalesOpportunityTrckCode;
	private boolean success;

	public SalesOpportunityTrckCodeAdded(SalesOpportunityTrckCode addedSalesOpportunityTrckCode, boolean success){
		this.addedSalesOpportunityTrckCode = addedSalesOpportunityTrckCode;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SalesOpportunityTrckCode getAddedSalesOpportunityTrckCode() {
		return addedSalesOpportunityTrckCode;
	}

}

package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityTrckCode.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityTrckCode.model.SalesOpportunityTrckCode;
public class SalesOpportunityTrckCodeDeleted implements Event{

	private boolean success;

	public SalesOpportunityTrckCodeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

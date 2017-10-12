package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityTrckCode.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityTrckCode.model.SalesOpportunityTrckCode;
public class SalesOpportunityTrckCodeUpdated implements Event{

	private boolean success;

	public SalesOpportunityTrckCodeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

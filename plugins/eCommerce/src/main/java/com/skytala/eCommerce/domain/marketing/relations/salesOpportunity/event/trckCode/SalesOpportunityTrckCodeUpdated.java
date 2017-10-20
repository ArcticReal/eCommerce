package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.trckCode;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.trckCode.SalesOpportunityTrckCode;
public class SalesOpportunityTrckCodeUpdated implements Event{

	private boolean success;

	public SalesOpportunityTrckCodeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

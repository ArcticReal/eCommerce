package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.SalesOpportunity;
public class SalesOpportunityDeleted implements Event{

	private boolean success;

	public SalesOpportunityDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

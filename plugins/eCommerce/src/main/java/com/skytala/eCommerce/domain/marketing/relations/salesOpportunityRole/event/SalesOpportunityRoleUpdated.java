package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityRole.model.SalesOpportunityRole;
public class SalesOpportunityRoleUpdated implements Event{

	private boolean success;

	public SalesOpportunityRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

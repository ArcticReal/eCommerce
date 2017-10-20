package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.role.SalesOpportunityRole;
public class SalesOpportunityRoleUpdated implements Event{

	private boolean success;

	public SalesOpportunityRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

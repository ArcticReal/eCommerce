package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.role.SalesOpportunityRole;
public class SalesOpportunityRoleDeleted implements Event{

	private boolean success;

	public SalesOpportunityRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

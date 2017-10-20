package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.role.SalesOpportunityRole;
public class SalesOpportunityRoleAdded implements Event{

	private SalesOpportunityRole addedSalesOpportunityRole;
	private boolean success;

	public SalesOpportunityRoleAdded(SalesOpportunityRole addedSalesOpportunityRole, boolean success){
		this.addedSalesOpportunityRole = addedSalesOpportunityRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SalesOpportunityRole getAddedSalesOpportunityRole() {
		return addedSalesOpportunityRole;
	}

}

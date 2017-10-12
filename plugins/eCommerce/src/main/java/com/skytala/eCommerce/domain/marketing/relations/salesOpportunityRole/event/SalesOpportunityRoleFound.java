package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityRole.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityRole.model.SalesOpportunityRole;
public class SalesOpportunityRoleFound implements Event{

	private List<SalesOpportunityRole> salesOpportunityRoles;

	public SalesOpportunityRoleFound(List<SalesOpportunityRole> salesOpportunityRoles) {
		this.salesOpportunityRoles = salesOpportunityRoles;
	}

	public List<SalesOpportunityRole> getSalesOpportunityRoles()	{
		return salesOpportunityRoles;
	}

}

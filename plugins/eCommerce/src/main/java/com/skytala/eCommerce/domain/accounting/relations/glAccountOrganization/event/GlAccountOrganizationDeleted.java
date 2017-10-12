package com.skytala.eCommerce.domain.accounting.relations.glAccountOrganization.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountOrganization.model.GlAccountOrganization;
public class GlAccountOrganizationDeleted implements Event{

	private boolean success;

	public GlAccountOrganizationDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

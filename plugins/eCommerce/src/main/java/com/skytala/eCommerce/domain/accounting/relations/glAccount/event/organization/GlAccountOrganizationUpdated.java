package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.organization;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.organization.GlAccountOrganization;
public class GlAccountOrganizationUpdated implements Event{

	private boolean success;

	public GlAccountOrganizationUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

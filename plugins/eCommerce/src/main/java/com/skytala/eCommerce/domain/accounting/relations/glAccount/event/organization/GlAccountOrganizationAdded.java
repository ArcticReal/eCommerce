package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.organization;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.organization.GlAccountOrganization;
public class GlAccountOrganizationAdded implements Event{

	private GlAccountOrganization addedGlAccountOrganization;
	private boolean success;

	public GlAccountOrganizationAdded(GlAccountOrganization addedGlAccountOrganization, boolean success){
		this.addedGlAccountOrganization = addedGlAccountOrganization;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GlAccountOrganization getAddedGlAccountOrganization() {
		return addedGlAccountOrganization;
	}

}

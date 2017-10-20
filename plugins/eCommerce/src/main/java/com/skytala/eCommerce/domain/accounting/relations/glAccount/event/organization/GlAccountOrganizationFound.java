package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.organization;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.organization.GlAccountOrganization;
public class GlAccountOrganizationFound implements Event{

	private List<GlAccountOrganization> glAccountOrganizations;

	public GlAccountOrganizationFound(List<GlAccountOrganization> glAccountOrganizations) {
		this.glAccountOrganizations = glAccountOrganizations;
	}

	public List<GlAccountOrganization> getGlAccountOrganizations()	{
		return glAccountOrganizations;
	}

}

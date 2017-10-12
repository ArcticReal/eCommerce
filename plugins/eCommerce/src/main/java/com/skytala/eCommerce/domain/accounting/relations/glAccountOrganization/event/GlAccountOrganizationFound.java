package com.skytala.eCommerce.domain.accounting.relations.glAccountOrganization.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountOrganization.model.GlAccountOrganization;
public class GlAccountOrganizationFound implements Event{

	private List<GlAccountOrganization> glAccountOrganizations;

	public GlAccountOrganizationFound(List<GlAccountOrganization> glAccountOrganizations) {
		this.glAccountOrganizations = glAccountOrganizations;
	}

	public List<GlAccountOrganization> getGlAccountOrganizations()	{
		return glAccountOrganizations;
	}

}

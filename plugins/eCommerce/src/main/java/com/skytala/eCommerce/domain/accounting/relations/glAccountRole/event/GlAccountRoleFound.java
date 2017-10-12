package com.skytala.eCommerce.domain.accounting.relations.glAccountRole.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountRole.model.GlAccountRole;
public class GlAccountRoleFound implements Event{

	private List<GlAccountRole> glAccountRoles;

	public GlAccountRoleFound(List<GlAccountRole> glAccountRoles) {
		this.glAccountRoles = glAccountRoles;
	}

	public List<GlAccountRole> getGlAccountRoles()	{
		return glAccountRoles;
	}

}

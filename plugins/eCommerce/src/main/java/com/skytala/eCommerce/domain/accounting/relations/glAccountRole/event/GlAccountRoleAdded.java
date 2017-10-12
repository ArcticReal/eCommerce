package com.skytala.eCommerce.domain.accounting.relations.glAccountRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountRole.model.GlAccountRole;
public class GlAccountRoleAdded implements Event{

	private GlAccountRole addedGlAccountRole;
	private boolean success;

	public GlAccountRoleAdded(GlAccountRole addedGlAccountRole, boolean success){
		this.addedGlAccountRole = addedGlAccountRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GlAccountRole getAddedGlAccountRole() {
		return addedGlAccountRole;
	}

}

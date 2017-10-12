package com.skytala.eCommerce.domain.accounting.relations.glAccountRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountRole.model.GlAccountRole;
public class GlAccountRoleUpdated implements Event{

	private boolean success;

	public GlAccountRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

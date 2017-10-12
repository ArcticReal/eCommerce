package com.skytala.eCommerce.domain.accounting.relations.glAccountRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountRole.model.GlAccountRole;
public class GlAccountRoleDeleted implements Event{

	private boolean success;

	public GlAccountRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

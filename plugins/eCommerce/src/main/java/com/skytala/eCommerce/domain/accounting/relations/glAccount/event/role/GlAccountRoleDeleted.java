package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.role.GlAccountRole;
public class GlAccountRoleDeleted implements Event{

	private boolean success;

	public GlAccountRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

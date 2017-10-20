package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.role.GlAccountRole;
public class GlAccountRoleUpdated implements Event{

	private boolean success;

	public GlAccountRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

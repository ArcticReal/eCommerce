package com.skytala.eCommerce.domain.accounting.relations.finAccount.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.role.FinAccountRole;
public class FinAccountRoleUpdated implements Event{

	private boolean success;

	public FinAccountRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

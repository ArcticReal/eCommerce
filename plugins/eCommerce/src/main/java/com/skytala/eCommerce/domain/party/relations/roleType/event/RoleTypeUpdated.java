package com.skytala.eCommerce.domain.party.relations.roleType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.roleType.model.RoleType;
public class RoleTypeUpdated implements Event{

	private boolean success;

	public RoleTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

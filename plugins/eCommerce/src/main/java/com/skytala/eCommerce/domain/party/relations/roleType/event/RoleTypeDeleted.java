package com.skytala.eCommerce.domain.party.relations.roleType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.roleType.model.RoleType;
public class RoleTypeDeleted implements Event{

	private boolean success;

	public RoleTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

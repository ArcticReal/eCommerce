package com.skytala.eCommerce.domain.roleType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.roleType.model.RoleType;
public class RoleTypeUpdated implements Event{

	private boolean success;

	public RoleTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.roleType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.roleType.model.RoleType;
public class RoleTypeAdded implements Event{

	private RoleType addedRoleType;
	private boolean success;

	public RoleTypeAdded(RoleType addedRoleType, boolean success){
		this.addedRoleType = addedRoleType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public RoleType getAddedRoleType() {
		return addedRoleType;
	}

}

package com.skytala.eCommerce.domain.login.relations.securityGroup.event.permission;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.securityGroup.model.permission.SecurityGroupPermission;
public class SecurityGroupPermissionUpdated implements Event{

	private boolean success;

	public SecurityGroupPermissionUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.login.relations.securityPermission.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.securityPermission.model.SecurityPermission;
public class SecurityPermissionUpdated implements Event{

	private boolean success;

	public SecurityPermissionUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

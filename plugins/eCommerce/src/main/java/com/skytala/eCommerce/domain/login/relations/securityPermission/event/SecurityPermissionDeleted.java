package com.skytala.eCommerce.domain.login.relations.securityPermission.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.securityPermission.model.SecurityPermission;
public class SecurityPermissionDeleted implements Event{

	private boolean success;

	public SecurityPermissionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

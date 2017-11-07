package com.skytala.eCommerce.domain.login.relations.securityPermission.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.securityPermission.model.SecurityPermission;
public class SecurityPermissionAdded implements Event{

	private SecurityPermission addedSecurityPermission;
	private boolean success;

	public SecurityPermissionAdded(SecurityPermission addedSecurityPermission, boolean success){
		this.addedSecurityPermission = addedSecurityPermission;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SecurityPermission getAddedSecurityPermission() {
		return addedSecurityPermission;
	}

}

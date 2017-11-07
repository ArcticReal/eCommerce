package com.skytala.eCommerce.domain.login.relations.securityGroup.event.permission;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.securityGroup.model.permission.SecurityGroupPermission;
public class SecurityGroupPermissionAdded implements Event{

	private SecurityGroupPermission addedSecurityGroupPermission;
	private boolean success;

	public SecurityGroupPermissionAdded(SecurityGroupPermission addedSecurityGroupPermission, boolean success){
		this.addedSecurityGroupPermission = addedSecurityGroupPermission;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SecurityGroupPermission getAddedSecurityGroupPermission() {
		return addedSecurityGroupPermission;
	}

}

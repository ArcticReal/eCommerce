package com.skytala.eCommerce.domain.login.relations.securityGroup.event.permission;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.securityGroup.model.permission.SecurityGroupPermission;
public class SecurityGroupPermissionFound implements Event{

	private List<SecurityGroupPermission> securityGroupPermissions;

	public SecurityGroupPermissionFound(List<SecurityGroupPermission> securityGroupPermissions) {
		this.securityGroupPermissions = securityGroupPermissions;
	}

	public List<SecurityGroupPermission> getSecurityGroupPermissions()	{
		return securityGroupPermissions;
	}

}

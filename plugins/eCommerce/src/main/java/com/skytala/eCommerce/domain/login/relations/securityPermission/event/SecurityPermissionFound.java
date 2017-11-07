package com.skytala.eCommerce.domain.login.relations.securityPermission.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.securityPermission.model.SecurityPermission;
public class SecurityPermissionFound implements Event{

	private List<SecurityPermission> securityPermissions;

	public SecurityPermissionFound(List<SecurityPermission> securityPermissions) {
		this.securityPermissions = securityPermissions;
	}

	public List<SecurityPermission> getSecurityPermissions()	{
		return securityPermissions;
	}

}

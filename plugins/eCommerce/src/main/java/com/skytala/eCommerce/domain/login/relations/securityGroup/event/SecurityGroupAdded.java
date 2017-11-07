package com.skytala.eCommerce.domain.login.relations.securityGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.securityGroup.model.SecurityGroup;
public class SecurityGroupAdded implements Event{

	private SecurityGroup addedSecurityGroup;
	private boolean success;

	public SecurityGroupAdded(SecurityGroup addedSecurityGroup, boolean success){
		this.addedSecurityGroup = addedSecurityGroup;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SecurityGroup getAddedSecurityGroup() {
		return addedSecurityGroup;
	}

}

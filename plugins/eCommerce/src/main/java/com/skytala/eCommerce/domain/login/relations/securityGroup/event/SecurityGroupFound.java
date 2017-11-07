package com.skytala.eCommerce.domain.login.relations.securityGroup.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.securityGroup.model.SecurityGroup;
public class SecurityGroupFound implements Event{

	private List<SecurityGroup> securityGroups;

	public SecurityGroupFound(List<SecurityGroup> securityGroups) {
		this.securityGroups = securityGroups;
	}

	public List<SecurityGroup> getSecurityGroups()	{
		return securityGroups;
	}

}

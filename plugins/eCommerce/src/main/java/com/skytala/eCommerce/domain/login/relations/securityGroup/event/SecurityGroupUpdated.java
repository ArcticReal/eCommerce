package com.skytala.eCommerce.domain.login.relations.securityGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.securityGroup.model.SecurityGroup;
public class SecurityGroupUpdated implements Event{

	private boolean success;

	public SecurityGroupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

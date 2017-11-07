package com.skytala.eCommerce.domain.login.relations.securityGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.securityGroup.model.SecurityGroup;
public class SecurityGroupDeleted implements Event{

	private boolean success;

	public SecurityGroupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

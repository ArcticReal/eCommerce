package com.skytala.eCommerce.domain.login.relations.userLogin.event.securityGroup;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.userLogin.model.securityGroup.UserLoginSecurityGroup;
public class UserLoginSecurityGroupDeleted implements Event{

	private boolean success;

	public UserLoginSecurityGroupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

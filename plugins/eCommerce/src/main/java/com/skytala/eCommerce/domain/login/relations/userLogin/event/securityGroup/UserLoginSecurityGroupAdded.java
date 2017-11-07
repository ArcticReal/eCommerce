package com.skytala.eCommerce.domain.login.relations.userLogin.event.securityGroup;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.userLogin.model.securityGroup.UserLoginSecurityGroup;
public class UserLoginSecurityGroupAdded implements Event{

	private UserLoginSecurityGroup addedUserLoginSecurityGroup;
	private boolean success;

	public UserLoginSecurityGroupAdded(UserLoginSecurityGroup addedUserLoginSecurityGroup, boolean success){
		this.addedUserLoginSecurityGroup = addedUserLoginSecurityGroup;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public UserLoginSecurityGroup getAddedUserLoginSecurityGroup() {
		return addedUserLoginSecurityGroup;
	}

}

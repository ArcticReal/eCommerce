package com.skytala.eCommerce.domain.login.relations.userLogin.event.securityGroup;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.userLogin.model.securityGroup.UserLoginSecurityGroup;
public class UserLoginSecurityGroupFound implements Event{

	private List<UserLoginSecurityGroup> userLoginSecurityGroups;

	public UserLoginSecurityGroupFound(List<UserLoginSecurityGroup> userLoginSecurityGroups) {
		this.userLoginSecurityGroups = userLoginSecurityGroups;
	}

	public List<UserLoginSecurityGroup> getUserLoginSecurityGroups()	{
		return userLoginSecurityGroups;
	}

}

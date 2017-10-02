package com.skytala.eCommerce.domain.userLogin.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.userLogin.model.UserLogin;
public class UserLoginFound implements Event{

	private List<UserLogin> userLogins;

	public UserLoginFound(List<UserLogin> userLogins) {
		this.userLogins = userLogins;
	}

	public List<UserLogin> getUserLogins()	{
		return userLogins;
	}

}

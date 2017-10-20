package com.skytala.eCommerce.domain.userLogin.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.userLogin.model.UserLogin;
public class UserLoginAdded implements Event{

	private UserLogin addedUserLogin;
	private boolean success;

	public UserLoginAdded(UserLogin addedUserLogin, boolean success){
		this.addedUserLogin = addedUserLogin;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public UserLogin getAddedUserLogin() {
		return addedUserLogin;
	}

}

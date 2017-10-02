package com.skytala.eCommerce.domain.userLogin.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.userLogin.model.UserLogin;
public class UserLoginDeleted implements Event{

	private boolean success;

	public UserLoginDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

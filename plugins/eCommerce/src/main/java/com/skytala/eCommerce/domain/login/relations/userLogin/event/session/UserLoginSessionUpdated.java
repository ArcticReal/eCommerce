package com.skytala.eCommerce.domain.login.relations.userLogin.event.session;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.userLogin.model.session.UserLoginSession;
public class UserLoginSessionUpdated implements Event{

	private boolean success;

	public UserLoginSessionUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

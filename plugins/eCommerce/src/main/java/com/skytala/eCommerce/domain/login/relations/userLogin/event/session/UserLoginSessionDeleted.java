package com.skytala.eCommerce.domain.login.relations.userLogin.event.session;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.userLogin.model.session.UserLoginSession;
public class UserLoginSessionDeleted implements Event{

	private boolean success;

	public UserLoginSessionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

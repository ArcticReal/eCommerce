package com.skytala.eCommerce.domain.login.relations.userLogin.event.history;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.userLogin.model.history.UserLoginHistory;
public class UserLoginHistoryUpdated implements Event{

	private boolean success;

	public UserLoginHistoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

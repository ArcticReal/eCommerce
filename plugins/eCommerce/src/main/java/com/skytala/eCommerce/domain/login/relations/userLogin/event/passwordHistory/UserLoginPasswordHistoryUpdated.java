package com.skytala.eCommerce.domain.login.relations.userLogin.event.passwordHistory;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.userLogin.model.passwordHistory.UserLoginPasswordHistory;
public class UserLoginPasswordHistoryUpdated implements Event{

	private boolean success;

	public UserLoginPasswordHistoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

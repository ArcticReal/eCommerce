package com.skytala.eCommerce.domain.login.relations.userLogin.event.passwordHistory;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.userLogin.model.passwordHistory.UserLoginPasswordHistory;
public class UserLoginPasswordHistoryAdded implements Event{

	private UserLoginPasswordHistory addedUserLoginPasswordHistory;
	private boolean success;

	public UserLoginPasswordHistoryAdded(UserLoginPasswordHistory addedUserLoginPasswordHistory, boolean success){
		this.addedUserLoginPasswordHistory = addedUserLoginPasswordHistory;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public UserLoginPasswordHistory getAddedUserLoginPasswordHistory() {
		return addedUserLoginPasswordHistory;
	}

}

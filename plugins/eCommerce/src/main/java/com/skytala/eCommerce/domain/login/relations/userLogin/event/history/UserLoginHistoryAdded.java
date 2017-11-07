package com.skytala.eCommerce.domain.login.relations.userLogin.event.history;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.userLogin.model.history.UserLoginHistory;
public class UserLoginHistoryAdded implements Event{

	private UserLoginHistory addedUserLoginHistory;
	private boolean success;

	public UserLoginHistoryAdded(UserLoginHistory addedUserLoginHistory, boolean success){
		this.addedUserLoginHistory = addedUserLoginHistory;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public UserLoginHistory getAddedUserLoginHistory() {
		return addedUserLoginHistory;
	}

}

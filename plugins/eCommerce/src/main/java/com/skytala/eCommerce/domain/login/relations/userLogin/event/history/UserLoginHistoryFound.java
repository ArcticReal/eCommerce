package com.skytala.eCommerce.domain.login.relations.userLogin.event.history;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.userLogin.model.history.UserLoginHistory;
public class UserLoginHistoryFound implements Event{

	private List<UserLoginHistory> userLoginHistorys;

	public UserLoginHistoryFound(List<UserLoginHistory> userLoginHistorys) {
		this.userLoginHistorys = userLoginHistorys;
	}

	public List<UserLoginHistory> getUserLoginHistorys()	{
		return userLoginHistorys;
	}

}

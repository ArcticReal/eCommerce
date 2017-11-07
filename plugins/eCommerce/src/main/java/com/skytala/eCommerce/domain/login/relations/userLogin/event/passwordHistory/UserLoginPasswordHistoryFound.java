package com.skytala.eCommerce.domain.login.relations.userLogin.event.passwordHistory;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.userLogin.model.passwordHistory.UserLoginPasswordHistory;
public class UserLoginPasswordHistoryFound implements Event{

	private List<UserLoginPasswordHistory> userLoginPasswordHistorys;

	public UserLoginPasswordHistoryFound(List<UserLoginPasswordHistory> userLoginPasswordHistorys) {
		this.userLoginPasswordHistorys = userLoginPasswordHistorys;
	}

	public List<UserLoginPasswordHistory> getUserLoginPasswordHistorys()	{
		return userLoginPasswordHistorys;
	}

}

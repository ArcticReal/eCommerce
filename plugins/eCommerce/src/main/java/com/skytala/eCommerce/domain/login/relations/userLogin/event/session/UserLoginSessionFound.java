package com.skytala.eCommerce.domain.login.relations.userLogin.event.session;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.userLogin.model.session.UserLoginSession;
public class UserLoginSessionFound implements Event{

	private List<UserLoginSession> userLoginSessions;

	public UserLoginSessionFound(List<UserLoginSession> userLoginSessions) {
		this.userLoginSessions = userLoginSessions;
	}

	public List<UserLoginSession> getUserLoginSessions()	{
		return userLoginSessions;
	}

}

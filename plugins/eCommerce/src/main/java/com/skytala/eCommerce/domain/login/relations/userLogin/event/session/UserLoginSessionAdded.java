package com.skytala.eCommerce.domain.login.relations.userLogin.event.session;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.userLogin.model.session.UserLoginSession;
public class UserLoginSessionAdded implements Event{

	private UserLoginSession addedUserLoginSession;
	private boolean success;

	public UserLoginSessionAdded(UserLoginSession addedUserLoginSession, boolean success){
		this.addedUserLoginSession = addedUserLoginSession;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public UserLoginSession getAddedUserLoginSession() {
		return addedUserLoginSession;
	}

}

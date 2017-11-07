package com.skytala.eCommerce.domain.login.relations.userLogin.event.securityQuestion;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.userLogin.model.securityQuestion.UserLoginSecurityQuestion;
public class UserLoginSecurityQuestionUpdated implements Event{

	private boolean success;

	public UserLoginSecurityQuestionUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

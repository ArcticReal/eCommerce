package com.skytala.eCommerce.domain.login.relations.userLogin.event.securityQuestion;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.userLogin.model.securityQuestion.UserLoginSecurityQuestion;
public class UserLoginSecurityQuestionAdded implements Event{

	private UserLoginSecurityQuestion addedUserLoginSecurityQuestion;
	private boolean success;

	public UserLoginSecurityQuestionAdded(UserLoginSecurityQuestion addedUserLoginSecurityQuestion, boolean success){
		this.addedUserLoginSecurityQuestion = addedUserLoginSecurityQuestion;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public UserLoginSecurityQuestion getAddedUserLoginSecurityQuestion() {
		return addedUserLoginSecurityQuestion;
	}

}

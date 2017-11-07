package com.skytala.eCommerce.domain.login.relations.userLogin.event.securityQuestion;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.userLogin.model.securityQuestion.UserLoginSecurityQuestion;
public class UserLoginSecurityQuestionFound implements Event{

	private List<UserLoginSecurityQuestion> userLoginSecurityQuestions;

	public UserLoginSecurityQuestionFound(List<UserLoginSecurityQuestion> userLoginSecurityQuestions) {
		this.userLoginSecurityQuestions = userLoginSecurityQuestions;
	}

	public List<UserLoginSecurityQuestion> getUserLoginSecurityQuestions()	{
		return userLoginSecurityQuestions;
	}

}

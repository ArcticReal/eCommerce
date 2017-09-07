package com.skytala.eCommerce.event;

import java.util.List;

import com.skytala.eCommerce.control.Event;
import com.skytala.eCommerce.entity.UserLogin;

public class UserLoginFound implements Event{

	private List<UserLogin> userLogins;

	public UserLoginFound(List<UserLogin> userLogins) {
		this.setUserLogins(userLogins);
	}

	public List<UserLogin> getUserLogins()	{
		return userLogins;
	}

	public void setUserLogins(List<UserLogin> userLogins)	{
		this.userLogins = userLogins;
	}
}

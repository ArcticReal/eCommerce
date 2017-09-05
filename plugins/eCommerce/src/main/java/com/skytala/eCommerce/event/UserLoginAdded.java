package com.skytala.eCommerce.event;

import com.skytala.eCommerce.control.Event;

public class UserLoginAdded implements Event {
	boolean success;
	
	public UserLoginAdded(boolean success){
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}

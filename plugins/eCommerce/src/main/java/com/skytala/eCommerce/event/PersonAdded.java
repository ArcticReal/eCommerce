package com.skytala.eCommerce.event;

import com.skytala.eCommerce.control.Event;

public class PersonAdded implements Event{
	
boolean success;
	
	public PersonAdded(boolean success){
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}
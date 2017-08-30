package com.skytala.eCommerce.event;

import com.skytala.eCommerce.control.Event;

public class ProductUpdated implements Event{

	boolean success;
	
	public ProductUpdated(boolean success){
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}

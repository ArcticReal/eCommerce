package com.skytala.eCommerce.event;

import com.skytala.eCommerce.control.Event;

public class ProductDeleted implements Event{

	boolean success;
	
	public ProductDeleted(boolean success){
		setSuccess(success);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}

package com.skytala.eCommerce.event;

import com.skytala.eCommerce.control.Event;

public class ProductAdded implements Event{

	boolean success;
	
	public ProductAdded(boolean success){
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}

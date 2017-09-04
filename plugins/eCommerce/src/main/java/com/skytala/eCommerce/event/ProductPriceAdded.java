package com.skytala.eCommerce.event;

import com.skytala.eCommerce.control.Event;

public class ProductPriceAdded implements Event{

	private boolean success;

	public ProductPriceAdded(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
	
}

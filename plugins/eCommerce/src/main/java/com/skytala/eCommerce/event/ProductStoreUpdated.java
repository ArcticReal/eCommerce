package com.skytala.eCommerce.event;

import com.skytala.eCommerce.control.Event;

public class ProductStoreUpdated implements Event {

	private boolean success;

	public ProductStoreUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}

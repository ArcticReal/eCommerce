package com.skytala.eCommerce.event;

import com.skytala.eCommerce.control.Event;

public class ProductPriceUpdated implements Event{

	private boolean success;

	public ProductPriceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}

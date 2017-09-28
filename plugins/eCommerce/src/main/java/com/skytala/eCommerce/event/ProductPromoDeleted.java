package com.skytala.eCommerce.event;

import com.skytala.eCommerce.control.Event;

public class ProductPromoDeleted implements Event{

	private boolean success;

	public ProductPromoDeleted(boolean success) {
		this.setSuccess(success);
	}

	public boolean isSuccess()	{
		return success;
	}

	public void setSuccess(boolean success)	{
		this.success = success;
	}
}

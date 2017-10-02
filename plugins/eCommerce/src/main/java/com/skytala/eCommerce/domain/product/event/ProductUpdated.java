package com.skytala.eCommerce.domain.product.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.model.Product;
public class ProductUpdated implements Event{

	private boolean success;

	public ProductUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

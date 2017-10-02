package com.skytala.eCommerce.domain.product.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.model.Product;
public class ProductDeleted implements Event{

	private boolean success;

	public ProductDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

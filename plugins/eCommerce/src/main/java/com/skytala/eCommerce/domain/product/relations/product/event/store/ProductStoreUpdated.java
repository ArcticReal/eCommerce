package com.skytala.eCommerce.domain.product.relations.product.event.store;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.store.ProductStore;
public class ProductStoreUpdated implements Event{

	private boolean success;

	public ProductStoreUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

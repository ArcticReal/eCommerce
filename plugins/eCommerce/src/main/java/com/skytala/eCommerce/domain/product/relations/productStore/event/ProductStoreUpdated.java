package com.skytala.eCommerce.domain.product.relations.productStore.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStore.model.ProductStore;
public class ProductStoreUpdated implements Event{

	private boolean success;

	public ProductStoreUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

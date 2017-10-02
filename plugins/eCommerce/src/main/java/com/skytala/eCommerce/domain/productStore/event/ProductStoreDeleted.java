package com.skytala.eCommerce.domain.productStore.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productStore.model.ProductStore;
public class ProductStoreDeleted implements Event{

	private boolean success;

	public ProductStoreDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

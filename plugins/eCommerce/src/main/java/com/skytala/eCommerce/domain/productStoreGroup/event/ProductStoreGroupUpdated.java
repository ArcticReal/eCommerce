package com.skytala.eCommerce.domain.productStoreGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productStoreGroup.model.ProductStoreGroup;
public class ProductStoreGroupUpdated implements Event{

	private boolean success;

	public ProductStoreGroupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.product.relations.product.event.storeGroup;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeGroup.ProductStoreGroup;
public class ProductStoreGroupDeleted implements Event{

	private boolean success;

	public ProductStoreGroupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.product.relations.productStoreGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreGroup.model.ProductStoreGroup;
public class ProductStoreGroupDeleted implements Event{

	private boolean success;

	public ProductStoreGroupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

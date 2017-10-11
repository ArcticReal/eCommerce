package com.skytala.eCommerce.domain.product.relations.productStoreGroupType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreGroupType.model.ProductStoreGroupType;
public class ProductStoreGroupTypeDeleted implements Event{

	private boolean success;

	public ProductStoreGroupTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
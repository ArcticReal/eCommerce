package com.skytala.eCommerce.domain.product.relations.product.event.storeGroupType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupType.ProductStoreGroupType;
public class ProductStoreGroupTypeDeleted implements Event{

	private boolean success;

	public ProductStoreGroupTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

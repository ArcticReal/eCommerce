package com.skytala.eCommerce.domain.product.relations.product.event.storeGroupType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupType.ProductStoreGroupType;
public class ProductStoreGroupTypeUpdated implements Event{

	private boolean success;

	public ProductStoreGroupTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

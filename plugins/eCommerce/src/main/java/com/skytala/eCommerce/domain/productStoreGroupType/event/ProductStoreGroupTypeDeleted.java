package com.skytala.eCommerce.domain.productStoreGroupType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productStoreGroupType.model.ProductStoreGroupType;
public class ProductStoreGroupTypeDeleted implements Event{

	private boolean success;

	public ProductStoreGroupTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

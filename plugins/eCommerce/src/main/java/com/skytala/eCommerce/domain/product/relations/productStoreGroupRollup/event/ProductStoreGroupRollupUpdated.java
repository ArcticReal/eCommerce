package com.skytala.eCommerce.domain.product.relations.productStoreGroupRollup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreGroupRollup.model.ProductStoreGroupRollup;
public class ProductStoreGroupRollupUpdated implements Event{

	private boolean success;

	public ProductStoreGroupRollupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

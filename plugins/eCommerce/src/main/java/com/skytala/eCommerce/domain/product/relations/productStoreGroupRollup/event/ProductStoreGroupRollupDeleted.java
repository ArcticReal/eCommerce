package com.skytala.eCommerce.domain.product.relations.productStoreGroupRollup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreGroupRollup.model.ProductStoreGroupRollup;
public class ProductStoreGroupRollupDeleted implements Event{

	private boolean success;

	public ProductStoreGroupRollupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

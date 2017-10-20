package com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRollup;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupRollup.ProductStoreGroupRollup;
public class ProductStoreGroupRollupDeleted implements Event{

	private boolean success;

	public ProductStoreGroupRollupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.product.relations.product.event.storeRole;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeRole.ProductStoreRole;
public class ProductStoreRoleDeleted implements Event{

	private boolean success;

	public ProductStoreRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

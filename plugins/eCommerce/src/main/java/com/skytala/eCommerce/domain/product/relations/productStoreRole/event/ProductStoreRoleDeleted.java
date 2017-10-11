package com.skytala.eCommerce.domain.product.relations.productStoreRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreRole.model.ProductStoreRole;
public class ProductStoreRoleDeleted implements Event{

	private boolean success;

	public ProductStoreRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

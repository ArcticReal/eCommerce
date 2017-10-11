package com.skytala.eCommerce.domain.product.relations.productStoreGroupRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreGroupRole.model.ProductStoreGroupRole;
public class ProductStoreGroupRoleDeleted implements Event{

	private boolean success;

	public ProductStoreGroupRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

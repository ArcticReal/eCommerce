package com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRole;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupRole.ProductStoreGroupRole;
public class ProductStoreGroupRoleDeleted implements Event{

	private boolean success;

	public ProductStoreGroupRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

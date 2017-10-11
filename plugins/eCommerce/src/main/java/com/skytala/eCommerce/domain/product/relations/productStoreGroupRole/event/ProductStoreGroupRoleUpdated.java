package com.skytala.eCommerce.domain.product.relations.productStoreGroupRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreGroupRole.model.ProductStoreGroupRole;
public class ProductStoreGroupRoleUpdated implements Event{

	private boolean success;

	public ProductStoreGroupRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

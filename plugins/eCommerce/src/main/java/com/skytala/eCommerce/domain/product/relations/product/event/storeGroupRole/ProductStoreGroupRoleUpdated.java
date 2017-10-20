package com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRole;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupRole.ProductStoreGroupRole;
public class ProductStoreGroupRoleUpdated implements Event{

	private boolean success;

	public ProductStoreGroupRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

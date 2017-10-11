package com.skytala.eCommerce.domain.product.relations.productRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productRole.model.ProductRole;
public class ProductRoleDeleted implements Event{

	private boolean success;

	public ProductRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.product.relations.product.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.role.ProductRole;
public class ProductRoleDeleted implements Event{

	private boolean success;

	public ProductRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.product.relations.product.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.role.ProductRole;
public class ProductRoleUpdated implements Event{

	private boolean success;

	public ProductRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

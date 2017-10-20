package com.skytala.eCommerce.domain.product.relations.product.event.categoryRole;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryRole.ProductCategoryRole;
public class ProductCategoryRoleDeleted implements Event{

	private boolean success;

	public ProductCategoryRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.product.relations.productCategoryRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryRole.model.ProductCategoryRole;
public class ProductCategoryRoleDeleted implements Event{

	private boolean success;

	public ProductCategoryRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

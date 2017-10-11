package com.skytala.eCommerce.domain.product.relations.productCategoryRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryRole.model.ProductCategoryRole;
public class ProductCategoryRoleUpdated implements Event{

	private boolean success;

	public ProductCategoryRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

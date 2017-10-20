package com.skytala.eCommerce.domain.product.relations.product.event.categoryGlAccount;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryGlAccount.ProductCategoryGlAccount;
public class ProductCategoryGlAccountUpdated implements Event{

	private boolean success;

	public ProductCategoryGlAccountUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

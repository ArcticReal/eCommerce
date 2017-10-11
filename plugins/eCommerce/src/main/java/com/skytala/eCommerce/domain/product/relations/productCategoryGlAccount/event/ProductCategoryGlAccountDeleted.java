package com.skytala.eCommerce.domain.product.relations.productCategoryGlAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryGlAccount.model.ProductCategoryGlAccount;
public class ProductCategoryGlAccountDeleted implements Event{

	private boolean success;

	public ProductCategoryGlAccountDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

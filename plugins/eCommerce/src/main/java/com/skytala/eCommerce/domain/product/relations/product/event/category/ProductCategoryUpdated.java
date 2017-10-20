package com.skytala.eCommerce.domain.product.relations.product.event.category;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.category.ProductCategory;
public class ProductCategoryUpdated implements Event{

	private boolean success;

	public ProductCategoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

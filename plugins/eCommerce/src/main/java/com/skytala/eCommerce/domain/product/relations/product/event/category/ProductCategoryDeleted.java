package com.skytala.eCommerce.domain.product.relations.product.event.category;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.category.ProductCategory;
public class ProductCategoryDeleted implements Event{

	private boolean success;

	public ProductCategoryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

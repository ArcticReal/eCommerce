package com.skytala.eCommerce.domain.product.relations.productCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategory.model.ProductCategory;
public class ProductCategoryUpdated implements Event{

	private boolean success;

	public ProductCategoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

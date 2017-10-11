package com.skytala.eCommerce.domain.product.relations.productCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategory.model.ProductCategory;
public class ProductCategoryDeleted implements Event{

	private boolean success;

	public ProductCategoryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

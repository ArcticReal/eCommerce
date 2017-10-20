package com.skytala.eCommerce.domain.product.relations.product.event.categoryType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryType.ProductCategoryType;
public class ProductCategoryTypeDeleted implements Event{

	private boolean success;

	public ProductCategoryTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

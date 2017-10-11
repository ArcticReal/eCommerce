package com.skytala.eCommerce.domain.product.relations.productCategoryType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryType.model.ProductCategoryType;
public class ProductCategoryTypeDeleted implements Event{

	private boolean success;

	public ProductCategoryTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

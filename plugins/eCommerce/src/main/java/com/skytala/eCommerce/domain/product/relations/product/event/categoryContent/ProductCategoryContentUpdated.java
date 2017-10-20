package com.skytala.eCommerce.domain.product.relations.product.event.categoryContent;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryContent.ProductCategoryContent;
public class ProductCategoryContentUpdated implements Event{

	private boolean success;

	public ProductCategoryContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
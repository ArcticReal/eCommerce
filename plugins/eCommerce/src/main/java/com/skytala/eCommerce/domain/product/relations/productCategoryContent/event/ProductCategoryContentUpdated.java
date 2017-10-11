package com.skytala.eCommerce.domain.product.relations.productCategoryContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryContent.model.ProductCategoryContent;
public class ProductCategoryContentUpdated implements Event{

	private boolean success;

	public ProductCategoryContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

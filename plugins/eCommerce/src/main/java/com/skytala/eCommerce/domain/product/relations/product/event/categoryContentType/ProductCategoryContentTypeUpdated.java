package com.skytala.eCommerce.domain.product.relations.product.event.categoryContentType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryContentType.ProductCategoryContentType;
public class ProductCategoryContentTypeUpdated implements Event{

	private boolean success;

	public ProductCategoryContentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

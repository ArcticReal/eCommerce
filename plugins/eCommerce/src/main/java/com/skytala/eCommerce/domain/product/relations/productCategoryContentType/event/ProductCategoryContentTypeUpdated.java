package com.skytala.eCommerce.domain.product.relations.productCategoryContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryContentType.model.ProductCategoryContentType;
public class ProductCategoryContentTypeUpdated implements Event{

	private boolean success;

	public ProductCategoryContentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

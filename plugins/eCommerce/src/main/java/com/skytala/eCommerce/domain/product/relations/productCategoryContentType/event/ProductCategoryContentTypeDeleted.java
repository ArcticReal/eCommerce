package com.skytala.eCommerce.domain.product.relations.productCategoryContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryContentType.model.ProductCategoryContentType;
public class ProductCategoryContentTypeDeleted implements Event{

	private boolean success;

	public ProductCategoryContentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

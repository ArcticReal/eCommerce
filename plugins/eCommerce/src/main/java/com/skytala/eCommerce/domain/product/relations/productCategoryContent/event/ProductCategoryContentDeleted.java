package com.skytala.eCommerce.domain.product.relations.productCategoryContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryContent.model.ProductCategoryContent;
public class ProductCategoryContentDeleted implements Event{

	private boolean success;

	public ProductCategoryContentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

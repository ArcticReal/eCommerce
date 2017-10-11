package com.skytala.eCommerce.domain.product.relations.productContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productContent.model.ProductContent;
public class ProductContentDeleted implements Event{

	private boolean success;

	public ProductContentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

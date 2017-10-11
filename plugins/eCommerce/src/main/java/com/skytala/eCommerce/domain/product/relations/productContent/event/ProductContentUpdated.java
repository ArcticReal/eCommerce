package com.skytala.eCommerce.domain.product.relations.productContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productContent.model.ProductContent;
public class ProductContentUpdated implements Event{

	private boolean success;

	public ProductContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

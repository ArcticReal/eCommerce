package com.skytala.eCommerce.domain.product.relations.product.event.content;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.content.ProductContent;
public class ProductContentUpdated implements Event{

	private boolean success;

	public ProductContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

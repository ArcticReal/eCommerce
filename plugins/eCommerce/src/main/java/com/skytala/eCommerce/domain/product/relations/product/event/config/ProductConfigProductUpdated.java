package com.skytala.eCommerce.domain.product.relations.product.event.config;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.config.ProductConfigProduct;
public class ProductConfigProductUpdated implements Event{

	private boolean success;

	public ProductConfigProductUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

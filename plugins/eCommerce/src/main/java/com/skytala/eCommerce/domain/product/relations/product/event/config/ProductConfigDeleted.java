package com.skytala.eCommerce.domain.product.relations.product.event.config;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.config.ProductConfig;
public class ProductConfigDeleted implements Event{

	private boolean success;

	public ProductConfigDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

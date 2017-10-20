package com.skytala.eCommerce.domain.product.relations.product.event.config;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.config.ProductConfigProduct;
public class ProductConfigProductDeleted implements Event{

	private boolean success;

	public ProductConfigProductDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

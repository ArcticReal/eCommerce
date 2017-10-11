package com.skytala.eCommerce.domain.product.relations.productConfig.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productConfig.model.ProductConfig;
public class ProductConfigDeleted implements Event{

	private boolean success;

	public ProductConfigDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.product.relations.productConfigConfig.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productConfigConfig.model.ProductConfigConfig;
public class ProductConfigConfigUpdated implements Event{

	private boolean success;

	public ProductConfigConfigUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

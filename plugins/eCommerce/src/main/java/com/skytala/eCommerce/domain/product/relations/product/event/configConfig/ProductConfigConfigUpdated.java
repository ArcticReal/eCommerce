package com.skytala.eCommerce.domain.product.relations.product.event.configConfig;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.configConfig.ProductConfigConfig;
public class ProductConfigConfigUpdated implements Event{

	private boolean success;

	public ProductConfigConfigUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

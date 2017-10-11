package com.skytala.eCommerce.domain.product.relations.productConfigConfig.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productConfigConfig.model.ProductConfigConfig;
public class ProductConfigConfigDeleted implements Event{

	private boolean success;

	public ProductConfigConfigDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

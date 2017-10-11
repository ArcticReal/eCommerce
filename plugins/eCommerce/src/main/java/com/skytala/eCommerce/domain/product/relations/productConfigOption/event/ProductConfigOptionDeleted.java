package com.skytala.eCommerce.domain.product.relations.productConfigOption.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productConfigOption.model.ProductConfigOption;
public class ProductConfigOptionDeleted implements Event{

	private boolean success;

	public ProductConfigOptionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

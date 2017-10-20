package com.skytala.eCommerce.domain.product.relations.product.event.configOption;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.configOption.ProductConfigOption;
public class ProductConfigOptionUpdated implements Event{

	private boolean success;

	public ProductConfigOptionUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

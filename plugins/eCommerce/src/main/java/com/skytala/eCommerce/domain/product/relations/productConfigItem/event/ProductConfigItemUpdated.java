package com.skytala.eCommerce.domain.product.relations.productConfigItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productConfigItem.model.ProductConfigItem;
public class ProductConfigItemUpdated implements Event{

	private boolean success;

	public ProductConfigItemUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

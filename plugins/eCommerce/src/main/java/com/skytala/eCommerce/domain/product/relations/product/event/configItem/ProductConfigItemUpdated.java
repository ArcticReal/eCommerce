package com.skytala.eCommerce.domain.product.relations.product.event.configItem;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.configItem.ProductConfigItem;
public class ProductConfigItemUpdated implements Event{

	private boolean success;

	public ProductConfigItemUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

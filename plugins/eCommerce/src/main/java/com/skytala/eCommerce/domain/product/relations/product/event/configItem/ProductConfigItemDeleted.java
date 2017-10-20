package com.skytala.eCommerce.domain.product.relations.product.event.configItem;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.configItem.ProductConfigItem;
public class ProductConfigItemDeleted implements Event{

	private boolean success;

	public ProductConfigItemDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

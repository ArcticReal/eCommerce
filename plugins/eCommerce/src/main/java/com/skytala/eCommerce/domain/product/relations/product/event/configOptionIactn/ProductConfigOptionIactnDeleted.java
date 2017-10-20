package com.skytala.eCommerce.domain.product.relations.product.event.configOptionIactn;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.configOptionIactn.ProductConfigOptionIactn;
public class ProductConfigOptionIactnDeleted implements Event{

	private boolean success;

	public ProductConfigOptionIactnDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

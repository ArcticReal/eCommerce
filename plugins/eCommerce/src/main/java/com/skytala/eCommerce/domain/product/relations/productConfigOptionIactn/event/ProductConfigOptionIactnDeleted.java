package com.skytala.eCommerce.domain.product.relations.productConfigOptionIactn.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productConfigOptionIactn.model.ProductConfigOptionIactn;
public class ProductConfigOptionIactnDeleted implements Event{

	private boolean success;

	public ProductConfigOptionIactnDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

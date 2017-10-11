package com.skytala.eCommerce.domain.product.relations.productConfigStats.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productConfigStats.model.ProductConfigStats;
public class ProductConfigStatsUpdated implements Event{

	private boolean success;

	public ProductConfigStatsUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

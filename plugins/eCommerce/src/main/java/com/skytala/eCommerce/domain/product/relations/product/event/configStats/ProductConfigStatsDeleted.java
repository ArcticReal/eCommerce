package com.skytala.eCommerce.domain.product.relations.product.event.configStats;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.configStats.ProductConfigStats;
public class ProductConfigStatsDeleted implements Event{

	private boolean success;

	public ProductConfigStatsDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

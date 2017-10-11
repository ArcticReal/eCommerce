package com.skytala.eCommerce.domain.product.relations.productConfigStats.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productConfigStats.model.ProductConfigStats;
public class ProductConfigStatsAdded implements Event{

	private ProductConfigStats addedProductConfigStats;
	private boolean success;

	public ProductConfigStatsAdded(ProductConfigStats addedProductConfigStats, boolean success){
		this.addedProductConfigStats = addedProductConfigStats;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductConfigStats getAddedProductConfigStats() {
		return addedProductConfigStats;
	}

}

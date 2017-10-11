package com.skytala.eCommerce.domain.product.relations.productFeatureIactn.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureIactn.model.ProductFeatureIactn;
public class ProductFeatureIactnUpdated implements Event{

	private boolean success;

	public ProductFeatureIactnUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

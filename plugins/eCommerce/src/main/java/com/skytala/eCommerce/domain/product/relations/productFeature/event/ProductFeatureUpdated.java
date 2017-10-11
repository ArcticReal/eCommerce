package com.skytala.eCommerce.domain.product.relations.productFeature.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeature.model.ProductFeature;
public class ProductFeatureUpdated implements Event{

	private boolean success;

	public ProductFeatureUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

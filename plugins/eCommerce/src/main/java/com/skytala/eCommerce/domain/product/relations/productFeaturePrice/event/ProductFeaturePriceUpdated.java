package com.skytala.eCommerce.domain.product.relations.productFeaturePrice.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeaturePrice.model.ProductFeaturePrice;
public class ProductFeaturePriceUpdated implements Event{

	private boolean success;

	public ProductFeaturePriceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

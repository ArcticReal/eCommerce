package com.skytala.eCommerce.domain.productFeatureType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productFeatureType.model.ProductFeatureType;
public class ProductFeatureTypeUpdated implements Event{

	private boolean success;

	public ProductFeatureTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

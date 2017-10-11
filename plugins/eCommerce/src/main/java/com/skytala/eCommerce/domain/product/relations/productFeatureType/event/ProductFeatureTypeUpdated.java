package com.skytala.eCommerce.domain.product.relations.productFeatureType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureType.model.ProductFeatureType;
public class ProductFeatureTypeUpdated implements Event{

	private boolean success;

	public ProductFeatureTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

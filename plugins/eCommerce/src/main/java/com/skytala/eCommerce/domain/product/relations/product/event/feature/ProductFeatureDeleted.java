package com.skytala.eCommerce.domain.product.relations.product.event.feature;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.feature.ProductFeature;
public class ProductFeatureDeleted implements Event{

	private boolean success;

	public ProductFeatureDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.product.relations.productFeatureDataResource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureDataResource.model.ProductFeatureDataResource;
public class ProductFeatureDataResourceUpdated implements Event{

	private boolean success;

	public ProductFeatureDataResourceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.product.relations.product.event.featureDataResource;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureDataResource.ProductFeatureDataResource;
public class ProductFeatureDataResourceUpdated implements Event{

	private boolean success;

	public ProductFeatureDataResourceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

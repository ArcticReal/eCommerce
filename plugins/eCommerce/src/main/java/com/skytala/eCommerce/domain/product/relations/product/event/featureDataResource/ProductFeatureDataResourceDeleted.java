package com.skytala.eCommerce.domain.product.relations.product.event.featureDataResource;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureDataResource.ProductFeatureDataResource;
public class ProductFeatureDataResourceDeleted implements Event{

	private boolean success;

	public ProductFeatureDataResourceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

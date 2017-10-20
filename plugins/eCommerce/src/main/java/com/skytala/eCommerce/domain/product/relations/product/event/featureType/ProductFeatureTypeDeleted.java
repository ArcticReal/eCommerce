package com.skytala.eCommerce.domain.product.relations.product.event.featureType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureType.ProductFeatureType;
public class ProductFeatureTypeDeleted implements Event{

	private boolean success;

	public ProductFeatureTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

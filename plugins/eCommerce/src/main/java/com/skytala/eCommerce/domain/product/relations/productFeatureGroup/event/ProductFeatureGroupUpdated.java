package com.skytala.eCommerce.domain.product.relations.productFeatureGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureGroup.model.ProductFeatureGroup;
public class ProductFeatureGroupUpdated implements Event{

	private boolean success;

	public ProductFeatureGroupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

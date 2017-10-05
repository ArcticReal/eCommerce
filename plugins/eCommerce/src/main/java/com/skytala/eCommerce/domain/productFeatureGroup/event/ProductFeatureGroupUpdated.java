package com.skytala.eCommerce.domain.productFeatureGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productFeatureGroup.model.ProductFeatureGroup;
public class ProductFeatureGroupUpdated implements Event{

	private boolean success;

	public ProductFeatureGroupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

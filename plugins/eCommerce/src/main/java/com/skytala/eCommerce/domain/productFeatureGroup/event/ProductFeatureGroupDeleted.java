package com.skytala.eCommerce.domain.productFeatureGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productFeatureGroup.model.ProductFeatureGroup;
public class ProductFeatureGroupDeleted implements Event{

	private boolean success;

	public ProductFeatureGroupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

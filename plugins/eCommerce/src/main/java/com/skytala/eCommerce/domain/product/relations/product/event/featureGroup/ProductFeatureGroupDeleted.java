package com.skytala.eCommerce.domain.product.relations.product.event.featureGroup;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureGroup.ProductFeatureGroup;
public class ProductFeatureGroupDeleted implements Event{

	private boolean success;

	public ProductFeatureGroupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

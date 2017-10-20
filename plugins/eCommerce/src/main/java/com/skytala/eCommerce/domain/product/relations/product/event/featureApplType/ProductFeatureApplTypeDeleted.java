package com.skytala.eCommerce.domain.product.relations.product.event.featureApplType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureApplType.ProductFeatureApplType;
public class ProductFeatureApplTypeDeleted implements Event{

	private boolean success;

	public ProductFeatureApplTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

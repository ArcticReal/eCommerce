package com.skytala.eCommerce.domain.product.relations.productFeatureApplType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureApplType.model.ProductFeatureApplType;
public class ProductFeatureApplTypeDeleted implements Event{

	private boolean success;

	public ProductFeatureApplTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
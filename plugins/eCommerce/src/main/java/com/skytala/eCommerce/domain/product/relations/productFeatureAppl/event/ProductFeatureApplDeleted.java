package com.skytala.eCommerce.domain.product.relations.productFeatureAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureAppl.model.ProductFeatureAppl;
public class ProductFeatureApplDeleted implements Event{

	private boolean success;

	public ProductFeatureApplDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

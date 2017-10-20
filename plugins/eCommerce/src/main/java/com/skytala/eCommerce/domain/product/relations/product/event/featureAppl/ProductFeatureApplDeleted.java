package com.skytala.eCommerce.domain.product.relations.product.event.featureAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureAppl.ProductFeatureAppl;
public class ProductFeatureApplDeleted implements Event{

	private boolean success;

	public ProductFeatureApplDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

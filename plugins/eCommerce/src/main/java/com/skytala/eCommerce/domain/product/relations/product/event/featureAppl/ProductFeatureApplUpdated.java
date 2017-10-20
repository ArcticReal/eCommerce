package com.skytala.eCommerce.domain.product.relations.product.event.featureAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureAppl.ProductFeatureAppl;
public class ProductFeatureApplUpdated implements Event{

	private boolean success;

	public ProductFeatureApplUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

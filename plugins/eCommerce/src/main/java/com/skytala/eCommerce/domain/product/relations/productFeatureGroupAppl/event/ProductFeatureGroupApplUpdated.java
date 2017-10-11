package com.skytala.eCommerce.domain.product.relations.productFeatureGroupAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureGroupAppl.model.ProductFeatureGroupAppl;
public class ProductFeatureGroupApplUpdated implements Event{

	private boolean success;

	public ProductFeatureGroupApplUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

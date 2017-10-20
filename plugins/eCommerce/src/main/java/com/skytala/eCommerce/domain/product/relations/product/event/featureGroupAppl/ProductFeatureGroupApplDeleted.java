package com.skytala.eCommerce.domain.product.relations.product.event.featureGroupAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureGroupAppl.ProductFeatureGroupAppl;
public class ProductFeatureGroupApplDeleted implements Event{

	private boolean success;

	public ProductFeatureGroupApplDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

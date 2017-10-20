package com.skytala.eCommerce.domain.product.relations.product.event.featurePrice;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featurePrice.ProductFeaturePrice;
public class ProductFeaturePriceDeleted implements Event{

	private boolean success;

	public ProductFeaturePriceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.product.relations.product.event.featureIactn;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureIactn.ProductFeatureIactn;
public class ProductFeatureIactnDeleted implements Event{

	private boolean success;

	public ProductFeatureIactnDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

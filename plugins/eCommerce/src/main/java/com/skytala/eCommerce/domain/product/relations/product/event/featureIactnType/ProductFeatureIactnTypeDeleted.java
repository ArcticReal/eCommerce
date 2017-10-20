package com.skytala.eCommerce.domain.product.relations.product.event.featureIactnType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureIactnType.ProductFeatureIactnType;
public class ProductFeatureIactnTypeDeleted implements Event{

	private boolean success;

	public ProductFeatureIactnTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

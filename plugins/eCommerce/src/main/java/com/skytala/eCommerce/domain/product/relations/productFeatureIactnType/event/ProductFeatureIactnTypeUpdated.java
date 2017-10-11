package com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.model.ProductFeatureIactnType;
public class ProductFeatureIactnTypeUpdated implements Event{

	private boolean success;

	public ProductFeatureIactnTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

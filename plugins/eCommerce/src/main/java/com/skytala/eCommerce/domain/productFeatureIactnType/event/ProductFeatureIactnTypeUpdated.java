package com.skytala.eCommerce.domain.productFeatureIactnType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productFeatureIactnType.model.ProductFeatureIactnType;
public class ProductFeatureIactnTypeUpdated implements Event{

	private boolean success;

	public ProductFeatureIactnTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

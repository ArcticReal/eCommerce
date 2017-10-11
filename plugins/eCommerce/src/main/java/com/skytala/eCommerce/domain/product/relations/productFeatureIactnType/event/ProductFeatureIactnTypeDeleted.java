package com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.model.ProductFeatureIactnType;
public class ProductFeatureIactnTypeDeleted implements Event{

	private boolean success;

	public ProductFeatureIactnTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

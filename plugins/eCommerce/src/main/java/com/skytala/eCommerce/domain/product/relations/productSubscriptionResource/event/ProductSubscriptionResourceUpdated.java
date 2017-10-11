package com.skytala.eCommerce.domain.product.relations.productSubscriptionResource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productSubscriptionResource.model.ProductSubscriptionResource;
public class ProductSubscriptionResourceUpdated implements Event{

	private boolean success;

	public ProductSubscriptionResourceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.product.relations.product.event.subscriptionResource;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.subscriptionResource.ProductSubscriptionResource;
public class ProductSubscriptionResourceUpdated implements Event{

	private boolean success;

	public ProductSubscriptionResourceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

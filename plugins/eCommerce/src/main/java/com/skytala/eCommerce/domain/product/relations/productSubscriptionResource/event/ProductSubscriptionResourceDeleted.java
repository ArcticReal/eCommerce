package com.skytala.eCommerce.domain.product.relations.productSubscriptionResource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productSubscriptionResource.model.ProductSubscriptionResource;
public class ProductSubscriptionResourceDeleted implements Event{

	private boolean success;

	public ProductSubscriptionResourceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

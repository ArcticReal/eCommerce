package com.skytala.eCommerce.domain.product.relations.product.event.subscriptionResource;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.subscriptionResource.ProductSubscriptionResource;
public class ProductSubscriptionResourceAdded implements Event{

	private ProductSubscriptionResource addedProductSubscriptionResource;
	private boolean success;

	public ProductSubscriptionResourceAdded(ProductSubscriptionResource addedProductSubscriptionResource, boolean success){
		this.addedProductSubscriptionResource = addedProductSubscriptionResource;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductSubscriptionResource getAddedProductSubscriptionResource() {
		return addedProductSubscriptionResource;
	}

}

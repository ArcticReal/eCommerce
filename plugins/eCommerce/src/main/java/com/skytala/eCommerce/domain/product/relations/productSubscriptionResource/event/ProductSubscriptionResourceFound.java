package com.skytala.eCommerce.domain.product.relations.productSubscriptionResource.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productSubscriptionResource.model.ProductSubscriptionResource;
public class ProductSubscriptionResourceFound implements Event{

	private List<ProductSubscriptionResource> productSubscriptionResources;

	public ProductSubscriptionResourceFound(List<ProductSubscriptionResource> productSubscriptionResources) {
		this.productSubscriptionResources = productSubscriptionResources;
	}

	public List<ProductSubscriptionResource> getProductSubscriptionResources()	{
		return productSubscriptionResources;
	}

}

package com.skytala.eCommerce.domain.product.relations.productFeatureDataResource.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureDataResource.model.ProductFeatureDataResource;
public class ProductFeatureDataResourceFound implements Event{

	private List<ProductFeatureDataResource> productFeatureDataResources;

	public ProductFeatureDataResourceFound(List<ProductFeatureDataResource> productFeatureDataResources) {
		this.productFeatureDataResources = productFeatureDataResources;
	}

	public List<ProductFeatureDataResource> getProductFeatureDataResources()	{
		return productFeatureDataResources;
	}

}

package com.skytala.eCommerce.domain.product.relations.product.event.featureDataResource;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureDataResource.ProductFeatureDataResource;
public class ProductFeatureDataResourceFound implements Event{

	private List<ProductFeatureDataResource> productFeatureDataResources;

	public ProductFeatureDataResourceFound(List<ProductFeatureDataResource> productFeatureDataResources) {
		this.productFeatureDataResources = productFeatureDataResources;
	}

	public List<ProductFeatureDataResource> getProductFeatureDataResources()	{
		return productFeatureDataResources;
	}

}

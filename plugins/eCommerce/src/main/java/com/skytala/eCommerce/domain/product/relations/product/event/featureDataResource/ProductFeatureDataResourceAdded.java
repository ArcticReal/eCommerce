package com.skytala.eCommerce.domain.product.relations.product.event.featureDataResource;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureDataResource.ProductFeatureDataResource;
public class ProductFeatureDataResourceAdded implements Event{

	private ProductFeatureDataResource addedProductFeatureDataResource;
	private boolean success;

	public ProductFeatureDataResourceAdded(ProductFeatureDataResource addedProductFeatureDataResource, boolean success){
		this.addedProductFeatureDataResource = addedProductFeatureDataResource;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductFeatureDataResource getAddedProductFeatureDataResource() {
		return addedProductFeatureDataResource;
	}

}

package com.skytala.eCommerce.domain.product.relations.productFeatureDataResource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureDataResource.model.ProductFeatureDataResource;
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

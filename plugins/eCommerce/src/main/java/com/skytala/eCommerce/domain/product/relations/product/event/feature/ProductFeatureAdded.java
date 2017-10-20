package com.skytala.eCommerce.domain.product.relations.product.event.feature;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.feature.ProductFeature;
public class ProductFeatureAdded implements Event{

	private ProductFeature addedProductFeature;
	private boolean success;

	public ProductFeatureAdded(ProductFeature addedProductFeature, boolean success){
		this.addedProductFeature = addedProductFeature;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductFeature getAddedProductFeature() {
		return addedProductFeature;
	}

}

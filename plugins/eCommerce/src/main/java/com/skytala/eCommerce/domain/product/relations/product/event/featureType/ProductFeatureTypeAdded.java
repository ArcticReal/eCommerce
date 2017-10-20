package com.skytala.eCommerce.domain.product.relations.product.event.featureType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureType.ProductFeatureType;
public class ProductFeatureTypeAdded implements Event{

	private ProductFeatureType addedProductFeatureType;
	private boolean success;

	public ProductFeatureTypeAdded(ProductFeatureType addedProductFeatureType, boolean success){
		this.addedProductFeatureType = addedProductFeatureType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductFeatureType getAddedProductFeatureType() {
		return addedProductFeatureType;
	}

}

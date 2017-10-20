package com.skytala.eCommerce.domain.product.relations.product.event.featureApplType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureApplType.ProductFeatureApplType;
public class ProductFeatureApplTypeAdded implements Event{

	private ProductFeatureApplType addedProductFeatureApplType;
	private boolean success;

	public ProductFeatureApplTypeAdded(ProductFeatureApplType addedProductFeatureApplType, boolean success){
		this.addedProductFeatureApplType = addedProductFeatureApplType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductFeatureApplType getAddedProductFeatureApplType() {
		return addedProductFeatureApplType;
	}

}

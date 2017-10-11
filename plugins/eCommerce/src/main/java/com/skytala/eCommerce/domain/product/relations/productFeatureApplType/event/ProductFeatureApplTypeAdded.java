package com.skytala.eCommerce.domain.product.relations.productFeatureApplType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureApplType.model.ProductFeatureApplType;
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

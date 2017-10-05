package com.skytala.eCommerce.domain.productFeatureGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productFeatureGroup.model.ProductFeatureGroup;
public class ProductFeatureGroupAdded implements Event{

	private ProductFeatureGroup addedProductFeatureGroup;
	private boolean success;

	public ProductFeatureGroupAdded(ProductFeatureGroup addedProductFeatureGroup, boolean success){
		this.addedProductFeatureGroup = addedProductFeatureGroup;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductFeatureGroup getAddedProductFeatureGroup() {
		return addedProductFeatureGroup;
	}

}

package com.skytala.eCommerce.domain.product.relations.product.event.featureGroup;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureGroup.ProductFeatureGroup;
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

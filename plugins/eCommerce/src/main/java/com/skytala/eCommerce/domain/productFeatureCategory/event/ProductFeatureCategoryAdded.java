package com.skytala.eCommerce.domain.productFeatureCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productFeatureCategory.model.ProductFeatureCategory;
public class ProductFeatureCategoryAdded implements Event{

	private ProductFeatureCategory addedProductFeatureCategory;
	private boolean success;

	public ProductFeatureCategoryAdded(ProductFeatureCategory addedProductFeatureCategory, boolean success){
		this.addedProductFeatureCategory = addedProductFeatureCategory;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductFeatureCategory getAddedProductFeatureCategory() {
		return addedProductFeatureCategory;
	}

}

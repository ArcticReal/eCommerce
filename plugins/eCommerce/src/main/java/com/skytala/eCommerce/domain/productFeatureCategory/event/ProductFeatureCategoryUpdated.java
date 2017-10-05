package com.skytala.eCommerce.domain.productFeatureCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productFeatureCategory.model.ProductFeatureCategory;
public class ProductFeatureCategoryUpdated implements Event{

	private boolean success;

	public ProductFeatureCategoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

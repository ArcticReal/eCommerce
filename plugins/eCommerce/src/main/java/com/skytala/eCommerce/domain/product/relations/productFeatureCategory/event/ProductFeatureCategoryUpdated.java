package com.skytala.eCommerce.domain.product.relations.productFeatureCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureCategory.model.ProductFeatureCategory;
public class ProductFeatureCategoryUpdated implements Event{

	private boolean success;

	public ProductFeatureCategoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

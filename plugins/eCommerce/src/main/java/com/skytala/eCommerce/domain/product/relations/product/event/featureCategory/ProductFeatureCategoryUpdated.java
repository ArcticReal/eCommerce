package com.skytala.eCommerce.domain.product.relations.product.event.featureCategory;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureCategory.ProductFeatureCategory;
public class ProductFeatureCategoryUpdated implements Event{

	private boolean success;

	public ProductFeatureCategoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

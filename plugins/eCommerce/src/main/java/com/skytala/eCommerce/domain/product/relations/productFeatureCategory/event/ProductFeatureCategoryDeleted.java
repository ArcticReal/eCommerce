package com.skytala.eCommerce.domain.product.relations.productFeatureCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureCategory.model.ProductFeatureCategory;
public class ProductFeatureCategoryDeleted implements Event{

	private boolean success;

	public ProductFeatureCategoryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

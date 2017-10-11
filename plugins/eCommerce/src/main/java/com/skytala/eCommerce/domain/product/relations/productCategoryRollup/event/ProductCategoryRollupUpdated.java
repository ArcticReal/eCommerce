package com.skytala.eCommerce.domain.product.relations.productCategoryRollup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryRollup.model.ProductCategoryRollup;
public class ProductCategoryRollupUpdated implements Event{

	private boolean success;

	public ProductCategoryRollupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

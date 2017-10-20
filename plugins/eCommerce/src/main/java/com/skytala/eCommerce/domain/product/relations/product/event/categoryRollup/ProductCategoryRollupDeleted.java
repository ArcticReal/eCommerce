package com.skytala.eCommerce.domain.product.relations.product.event.categoryRollup;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryRollup.ProductCategoryRollup;
public class ProductCategoryRollupDeleted implements Event{

	private boolean success;

	public ProductCategoryRollupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

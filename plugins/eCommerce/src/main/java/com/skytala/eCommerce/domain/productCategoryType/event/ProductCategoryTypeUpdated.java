package com.skytala.eCommerce.domain.productCategoryType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productCategoryType.model.ProductCategoryType;
public class ProductCategoryTypeUpdated implements Event{

	private boolean success;

	public ProductCategoryTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

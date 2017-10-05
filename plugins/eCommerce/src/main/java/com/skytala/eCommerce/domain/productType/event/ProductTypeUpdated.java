package com.skytala.eCommerce.domain.productType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productType.model.ProductType;
public class ProductTypeUpdated implements Event{

	private boolean success;

	public ProductTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

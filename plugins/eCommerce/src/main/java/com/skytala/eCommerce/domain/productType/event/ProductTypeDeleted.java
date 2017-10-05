package com.skytala.eCommerce.domain.productType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productType.model.ProductType;
public class ProductTypeDeleted implements Event{

	private boolean success;

	public ProductTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.product.relations.productCalculatedInfo.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCalculatedInfo.model.ProductCalculatedInfo;
public class ProductCalculatedInfoUpdated implements Event{

	private boolean success;

	public ProductCalculatedInfoUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

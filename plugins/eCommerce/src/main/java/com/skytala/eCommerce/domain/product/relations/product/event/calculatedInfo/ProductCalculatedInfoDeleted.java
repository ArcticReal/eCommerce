package com.skytala.eCommerce.domain.product.relations.product.event.calculatedInfo;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.calculatedInfo.ProductCalculatedInfo;
public class ProductCalculatedInfoDeleted implements Event{

	private boolean success;

	public ProductCalculatedInfoDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

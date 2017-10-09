package com.skytala.eCommerce.domain.productAverageCostType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productAverageCostType.model.ProductAverageCostType;
public class ProductAverageCostTypeUpdated implements Event{

	private boolean success;

	public ProductAverageCostTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
package com.skytala.eCommerce.domain.accounting.relations.productAverageCost.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.model.ProductAverageCost;
public class ProductAverageCostUpdated implements Event{

	private boolean success;

	public ProductAverageCostUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

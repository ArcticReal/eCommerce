package com.skytala.eCommerce.domain.accounting.relations.productAverageCost.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.model.ProductAverageCost;
public class ProductAverageCostDeleted implements Event{

	private boolean success;

	public ProductAverageCostDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

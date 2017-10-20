package com.skytala.eCommerce.domain.accounting.relations.productAverageCost.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.model.type.ProductAverageCostType;
public class ProductAverageCostTypeDeleted implements Event{

	private boolean success;

	public ProductAverageCostTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

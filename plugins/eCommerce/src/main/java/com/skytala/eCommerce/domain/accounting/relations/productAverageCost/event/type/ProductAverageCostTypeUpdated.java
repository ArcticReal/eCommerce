package com.skytala.eCommerce.domain.accounting.relations.productAverageCost.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.model.type.ProductAverageCostType;
public class ProductAverageCostTypeUpdated implements Event{

	private boolean success;

	public ProductAverageCostTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

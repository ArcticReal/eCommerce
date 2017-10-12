package com.skytala.eCommerce.domain.accounting.relations.productAverageCostType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.productAverageCostType.model.ProductAverageCostType;
public class ProductAverageCostTypeDeleted implements Event{

	private boolean success;

	public ProductAverageCostTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

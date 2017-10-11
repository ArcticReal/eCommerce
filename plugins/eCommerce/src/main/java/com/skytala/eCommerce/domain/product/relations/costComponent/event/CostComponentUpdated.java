package com.skytala.eCommerce.domain.product.relations.costComponent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponent.model.CostComponent;
public class CostComponentUpdated implements Event{

	private boolean success;

	public CostComponentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

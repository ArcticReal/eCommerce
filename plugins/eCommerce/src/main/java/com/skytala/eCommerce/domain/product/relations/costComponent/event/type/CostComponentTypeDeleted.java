package com.skytala.eCommerce.domain.product.relations.costComponent.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponent.model.type.CostComponentType;
public class CostComponentTypeDeleted implements Event{

	private boolean success;

	public CostComponentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

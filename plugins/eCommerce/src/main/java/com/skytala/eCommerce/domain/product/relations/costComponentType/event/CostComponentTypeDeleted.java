package com.skytala.eCommerce.domain.product.relations.costComponentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.costComponentType.model.CostComponentType;
public class CostComponentTypeDeleted implements Event{

	private boolean success;

	public CostComponentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

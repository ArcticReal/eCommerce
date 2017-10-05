package com.skytala.eCommerce.domain.costComponentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.costComponentType.model.CostComponentType;
public class CostComponentTypeDeleted implements Event{

	private boolean success;

	public CostComponentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.costComponent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.costComponent.model.CostComponent;
public class CostComponentDeleted implements Event{

	private boolean success;

	public CostComponentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.costComponent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.costComponent.model.CostComponent;
public class CostComponentUpdated implements Event{

	private boolean success;

	public CostComponentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

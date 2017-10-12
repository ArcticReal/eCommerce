package com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeRate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeRate.model.EmplPositionTypeRate;
public class EmplPositionTypeRateUpdated implements Event{

	private boolean success;

	public EmplPositionTypeRateUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

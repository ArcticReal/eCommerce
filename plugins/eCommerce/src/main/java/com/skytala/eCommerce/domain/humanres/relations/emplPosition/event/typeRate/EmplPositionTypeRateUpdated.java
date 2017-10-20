package com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.typeRate;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.typeRate.EmplPositionTypeRate;
public class EmplPositionTypeRateUpdated implements Event{

	private boolean success;

	public EmplPositionTypeRateUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

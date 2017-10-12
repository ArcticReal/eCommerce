package com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeRate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeRate.model.EmplPositionTypeRate;
public class EmplPositionTypeRateDeleted implements Event{

	private boolean success;

	public EmplPositionTypeRateDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

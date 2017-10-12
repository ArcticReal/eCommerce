package com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeRate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeRate.model.EmplPositionTypeRate;
public class EmplPositionTypeRateAdded implements Event{

	private EmplPositionTypeRate addedEmplPositionTypeRate;
	private boolean success;

	public EmplPositionTypeRateAdded(EmplPositionTypeRate addedEmplPositionTypeRate, boolean success){
		this.addedEmplPositionTypeRate = addedEmplPositionTypeRate;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public EmplPositionTypeRate getAddedEmplPositionTypeRate() {
		return addedEmplPositionTypeRate;
	}

}

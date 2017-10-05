package com.skytala.eCommerce.domain.accommodationSpot.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accommodationSpot.model.AccommodationSpot;
public class AccommodationSpotUpdated implements Event{

	private boolean success;

	public AccommodationSpotUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

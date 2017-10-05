package com.skytala.eCommerce.domain.accommodationMap.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accommodationMap.model.AccommodationMap;
public class AccommodationMapUpdated implements Event{

	private boolean success;

	public AccommodationMapUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

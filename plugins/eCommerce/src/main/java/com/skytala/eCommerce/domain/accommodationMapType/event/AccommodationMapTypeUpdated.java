package com.skytala.eCommerce.domain.accommodationMapType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accommodationMapType.model.AccommodationMapType;
public class AccommodationMapTypeUpdated implements Event{

	private boolean success;

	public AccommodationMapTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

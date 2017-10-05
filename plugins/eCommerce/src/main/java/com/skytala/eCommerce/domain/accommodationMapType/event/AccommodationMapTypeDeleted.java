package com.skytala.eCommerce.domain.accommodationMapType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accommodationMapType.model.AccommodationMapType;
public class AccommodationMapTypeDeleted implements Event{

	private boolean success;

	public AccommodationMapTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

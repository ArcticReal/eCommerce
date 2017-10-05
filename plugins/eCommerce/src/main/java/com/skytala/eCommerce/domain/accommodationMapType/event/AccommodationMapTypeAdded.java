package com.skytala.eCommerce.domain.accommodationMapType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accommodationMapType.model.AccommodationMapType;
public class AccommodationMapTypeAdded implements Event{

	private AccommodationMapType addedAccommodationMapType;
	private boolean success;

	public AccommodationMapTypeAdded(AccommodationMapType addedAccommodationMapType, boolean success){
		this.addedAccommodationMapType = addedAccommodationMapType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AccommodationMapType getAddedAccommodationMapType() {
		return addedAccommodationMapType;
	}

}

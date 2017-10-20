package com.skytala.eCommerce.domain.accounting.relations.accommodationMap.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.model.type.AccommodationMapType;
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

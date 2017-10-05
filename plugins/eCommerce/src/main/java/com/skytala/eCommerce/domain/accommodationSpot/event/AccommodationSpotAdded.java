package com.skytala.eCommerce.domain.accommodationSpot.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accommodationSpot.model.AccommodationSpot;
public class AccommodationSpotAdded implements Event{

	private AccommodationSpot addedAccommodationSpot;
	private boolean success;

	public AccommodationSpotAdded(AccommodationSpot addedAccommodationSpot, boolean success){
		this.addedAccommodationSpot = addedAccommodationSpot;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AccommodationSpot getAddedAccommodationSpot() {
		return addedAccommodationSpot;
	}

}

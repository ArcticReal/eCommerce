package com.skytala.eCommerce.domain.accounting.relations.accommodationSpot.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.accommodationSpot.model.AccommodationSpot;
public class AccommodationSpotFound implements Event{

	private List<AccommodationSpot> accommodationSpots;

	public AccommodationSpotFound(List<AccommodationSpot> accommodationSpots) {
		this.accommodationSpots = accommodationSpots;
	}

	public List<AccommodationSpot> getAccommodationSpots()	{
		return accommodationSpots;
	}

}

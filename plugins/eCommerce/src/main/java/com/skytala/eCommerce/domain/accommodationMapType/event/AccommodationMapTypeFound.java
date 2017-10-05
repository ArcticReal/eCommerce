package com.skytala.eCommerce.domain.accommodationMapType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accommodationMapType.model.AccommodationMapType;
public class AccommodationMapTypeFound implements Event{

	private List<AccommodationMapType> accommodationMapTypes;

	public AccommodationMapTypeFound(List<AccommodationMapType> accommodationMapTypes) {
		this.accommodationMapTypes = accommodationMapTypes;
	}

	public List<AccommodationMapType> getAccommodationMapTypes()	{
		return accommodationMapTypes;
	}

}

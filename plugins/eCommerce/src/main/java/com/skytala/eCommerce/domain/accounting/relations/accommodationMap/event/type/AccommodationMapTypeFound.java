package com.skytala.eCommerce.domain.accounting.relations.accommodationMap.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.model.type.AccommodationMapType;
public class AccommodationMapTypeFound implements Event{

	private List<AccommodationMapType> accommodationMapTypes;

	public AccommodationMapTypeFound(List<AccommodationMapType> accommodationMapTypes) {
		this.accommodationMapTypes = accommodationMapTypes;
	}

	public List<AccommodationMapType> getAccommodationMapTypes()	{
		return accommodationMapTypes;
	}

}

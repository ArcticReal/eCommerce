package com.skytala.eCommerce.domain.accounting.relations.accommodationMap.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.model.AccommodationMap;
public class AccommodationMapFound implements Event{

	private List<AccommodationMap> accommodationMaps;

	public AccommodationMapFound(List<AccommodationMap> accommodationMaps) {
		this.accommodationMaps = accommodationMaps;
	}

	public List<AccommodationMap> getAccommodationMaps()	{
		return accommodationMaps;
	}

}

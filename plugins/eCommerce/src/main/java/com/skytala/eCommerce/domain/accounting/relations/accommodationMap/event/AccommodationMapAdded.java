package com.skytala.eCommerce.domain.accounting.relations.accommodationMap.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.model.AccommodationMap;
public class AccommodationMapAdded implements Event{

	private AccommodationMap addedAccommodationMap;
	private boolean success;

	public AccommodationMapAdded(AccommodationMap addedAccommodationMap, boolean success){
		this.addedAccommodationMap = addedAccommodationMap;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AccommodationMap getAddedAccommodationMap() {
		return addedAccommodationMap;
	}

}

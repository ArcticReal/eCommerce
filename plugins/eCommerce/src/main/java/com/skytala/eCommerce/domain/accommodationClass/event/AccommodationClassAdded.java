package com.skytala.eCommerce.domain.accommodationClass.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accommodationClass.model.AccommodationClass;
public class AccommodationClassAdded implements Event{

	private AccommodationClass addedAccommodationClass;
	private boolean success;

	public AccommodationClassAdded(AccommodationClass addedAccommodationClass, boolean success){
		this.addedAccommodationClass = addedAccommodationClass;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AccommodationClass getAddedAccommodationClass() {
		return addedAccommodationClass;
	}

}

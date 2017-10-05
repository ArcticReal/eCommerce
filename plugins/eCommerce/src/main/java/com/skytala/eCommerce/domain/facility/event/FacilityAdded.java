package com.skytala.eCommerce.domain.facility.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.facility.model.Facility;
public class FacilityAdded implements Event{

	private Facility addedFacility;
	private boolean success;

	public FacilityAdded(Facility addedFacility, boolean success){
		this.addedFacility = addedFacility;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Facility getAddedFacility() {
		return addedFacility;
	}

}

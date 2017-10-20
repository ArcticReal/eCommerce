package com.skytala.eCommerce.domain.product.relations.facility.event.location;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.location.FacilityLocation;
public class FacilityLocationAdded implements Event{

	private FacilityLocation addedFacilityLocation;
	private boolean success;

	public FacilityLocationAdded(FacilityLocation addedFacilityLocation, boolean success){
		this.addedFacilityLocation = addedFacilityLocation;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FacilityLocation getAddedFacilityLocation() {
		return addedFacilityLocation;
	}

}

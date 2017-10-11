package com.skytala.eCommerce.domain.product.relations.facilityLocation.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityLocation.model.FacilityLocation;
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

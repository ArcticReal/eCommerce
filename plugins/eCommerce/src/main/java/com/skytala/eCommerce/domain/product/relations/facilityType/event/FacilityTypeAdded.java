package com.skytala.eCommerce.domain.product.relations.facilityType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityType.model.FacilityType;
public class FacilityTypeAdded implements Event{

	private FacilityType addedFacilityType;
	private boolean success;

	public FacilityTypeAdded(FacilityType addedFacilityType, boolean success){
		this.addedFacilityType = addedFacilityType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FacilityType getAddedFacilityType() {
		return addedFacilityType;
	}

}

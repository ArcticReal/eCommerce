package com.skytala.eCommerce.domain.product.relations.facility.event.locationGeoPoint;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.locationGeoPoint.FacilityLocationGeoPoint;
public class FacilityLocationGeoPointAdded implements Event{

	private FacilityLocationGeoPoint addedFacilityLocationGeoPoint;
	private boolean success;

	public FacilityLocationGeoPointAdded(FacilityLocationGeoPoint addedFacilityLocationGeoPoint, boolean success){
		this.addedFacilityLocationGeoPoint = addedFacilityLocationGeoPoint;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FacilityLocationGeoPoint getAddedFacilityLocationGeoPoint() {
		return addedFacilityLocationGeoPoint;
	}

}

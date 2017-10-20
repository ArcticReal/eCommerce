package com.skytala.eCommerce.domain.product.relations.facility.event.locationGeoPoint;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.locationGeoPoint.FacilityLocationGeoPoint;
public class FacilityLocationGeoPointFound implements Event{

	private List<FacilityLocationGeoPoint> facilityLocationGeoPoints;

	public FacilityLocationGeoPointFound(List<FacilityLocationGeoPoint> facilityLocationGeoPoints) {
		this.facilityLocationGeoPoints = facilityLocationGeoPoints;
	}

	public List<FacilityLocationGeoPoint> getFacilityLocationGeoPoints()	{
		return facilityLocationGeoPoints;
	}

}

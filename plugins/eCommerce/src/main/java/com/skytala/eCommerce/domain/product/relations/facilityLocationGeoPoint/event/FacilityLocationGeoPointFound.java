package com.skytala.eCommerce.domain.product.relations.facilityLocationGeoPoint.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityLocationGeoPoint.model.FacilityLocationGeoPoint;
public class FacilityLocationGeoPointFound implements Event{

	private List<FacilityLocationGeoPoint> facilityLocationGeoPoints;

	public FacilityLocationGeoPointFound(List<FacilityLocationGeoPoint> facilityLocationGeoPoints) {
		this.facilityLocationGeoPoints = facilityLocationGeoPoints;
	}

	public List<FacilityLocationGeoPoint> getFacilityLocationGeoPoints()	{
		return facilityLocationGeoPoints;
	}

}

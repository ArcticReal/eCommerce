package com.skytala.eCommerce.domain.product.relations.facility.event.location;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.location.FacilityLocation;
public class FacilityLocationFound implements Event{

	private List<FacilityLocation> facilityLocations;

	public FacilityLocationFound(List<FacilityLocation> facilityLocations) {
		this.facilityLocations = facilityLocations;
	}

	public List<FacilityLocation> getFacilityLocations()	{
		return facilityLocations;
	}

}

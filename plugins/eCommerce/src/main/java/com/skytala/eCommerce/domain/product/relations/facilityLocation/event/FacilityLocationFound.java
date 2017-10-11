package com.skytala.eCommerce.domain.product.relations.facilityLocation.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityLocation.model.FacilityLocation;
public class FacilityLocationFound implements Event{

	private List<FacilityLocation> facilityLocations;

	public FacilityLocationFound(List<FacilityLocation> facilityLocations) {
		this.facilityLocations = facilityLocations;
	}

	public List<FacilityLocation> getFacilityLocations()	{
		return facilityLocations;
	}

}

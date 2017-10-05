package com.skytala.eCommerce.domain.facilityType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.facilityType.model.FacilityType;
public class FacilityTypeFound implements Event{

	private List<FacilityType> facilityTypes;

	public FacilityTypeFound(List<FacilityType> facilityTypes) {
		this.facilityTypes = facilityTypes;
	}

	public List<FacilityType> getFacilityTypes()	{
		return facilityTypes;
	}

}

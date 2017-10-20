package com.skytala.eCommerce.domain.product.relations.facility.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.type.FacilityType;
public class FacilityTypeFound implements Event{

	private List<FacilityType> facilityTypes;

	public FacilityTypeFound(List<FacilityType> facilityTypes) {
		this.facilityTypes = facilityTypes;
	}

	public List<FacilityType> getFacilityTypes()	{
		return facilityTypes;
	}

}

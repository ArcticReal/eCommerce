package com.skytala.eCommerce.domain.product.relations.facilityGroupType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityGroupType.model.FacilityGroupType;
public class FacilityGroupTypeFound implements Event{

	private List<FacilityGroupType> facilityGroupTypes;

	public FacilityGroupTypeFound(List<FacilityGroupType> facilityGroupTypes) {
		this.facilityGroupTypes = facilityGroupTypes;
	}

	public List<FacilityGroupType> getFacilityGroupTypes()	{
		return facilityGroupTypes;
	}

}

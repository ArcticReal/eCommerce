package com.skytala.eCommerce.domain.facilityGroup.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.facilityGroup.model.FacilityGroup;
public class FacilityGroupFound implements Event{

	private List<FacilityGroup> facilityGroups;

	public FacilityGroupFound(List<FacilityGroup> facilityGroups) {
		this.facilityGroups = facilityGroups;
	}

	public List<FacilityGroup> getFacilityGroups()	{
		return facilityGroups;
	}

}

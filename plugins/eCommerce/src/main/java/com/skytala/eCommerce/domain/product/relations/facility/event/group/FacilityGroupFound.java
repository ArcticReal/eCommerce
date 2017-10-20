package com.skytala.eCommerce.domain.product.relations.facility.event.group;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.group.FacilityGroup;
public class FacilityGroupFound implements Event{

	private List<FacilityGroup> facilityGroups;

	public FacilityGroupFound(List<FacilityGroup> facilityGroups) {
		this.facilityGroups = facilityGroups;
	}

	public List<FacilityGroup> getFacilityGroups()	{
		return facilityGroups;
	}

}

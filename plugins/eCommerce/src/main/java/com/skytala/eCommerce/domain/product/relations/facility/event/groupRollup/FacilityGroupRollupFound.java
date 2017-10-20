package com.skytala.eCommerce.domain.product.relations.facility.event.groupRollup;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.groupRollup.FacilityGroupRollup;
public class FacilityGroupRollupFound implements Event{

	private List<FacilityGroupRollup> facilityGroupRollups;

	public FacilityGroupRollupFound(List<FacilityGroupRollup> facilityGroupRollups) {
		this.facilityGroupRollups = facilityGroupRollups;
	}

	public List<FacilityGroupRollup> getFacilityGroupRollups()	{
		return facilityGroupRollups;
	}

}

package com.skytala.eCommerce.domain.product.relations.facilityGroupRollup.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityGroupRollup.model.FacilityGroupRollup;
public class FacilityGroupRollupFound implements Event{

	private List<FacilityGroupRollup> facilityGroupRollups;

	public FacilityGroupRollupFound(List<FacilityGroupRollup> facilityGroupRollups) {
		this.facilityGroupRollups = facilityGroupRollups;
	}

	public List<FacilityGroupRollup> getFacilityGroupRollups()	{
		return facilityGroupRollups;
	}

}

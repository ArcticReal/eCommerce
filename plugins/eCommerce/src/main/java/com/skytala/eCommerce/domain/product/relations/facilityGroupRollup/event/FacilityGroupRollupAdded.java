package com.skytala.eCommerce.domain.product.relations.facilityGroupRollup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityGroupRollup.model.FacilityGroupRollup;
public class FacilityGroupRollupAdded implements Event{

	private FacilityGroupRollup addedFacilityGroupRollup;
	private boolean success;

	public FacilityGroupRollupAdded(FacilityGroupRollup addedFacilityGroupRollup, boolean success){
		this.addedFacilityGroupRollup = addedFacilityGroupRollup;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FacilityGroupRollup getAddedFacilityGroupRollup() {
		return addedFacilityGroupRollup;
	}

}

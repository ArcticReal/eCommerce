package com.skytala.eCommerce.domain.product.relations.facilityGroupType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityGroupType.model.FacilityGroupType;
public class FacilityGroupTypeAdded implements Event{

	private FacilityGroupType addedFacilityGroupType;
	private boolean success;

	public FacilityGroupTypeAdded(FacilityGroupType addedFacilityGroupType, boolean success){
		this.addedFacilityGroupType = addedFacilityGroupType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FacilityGroupType getAddedFacilityGroupType() {
		return addedFacilityGroupType;
	}

}

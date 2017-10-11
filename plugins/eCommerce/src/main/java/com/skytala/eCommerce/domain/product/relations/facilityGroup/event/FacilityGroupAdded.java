package com.skytala.eCommerce.domain.product.relations.facilityGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityGroup.model.FacilityGroup;
public class FacilityGroupAdded implements Event{

	private FacilityGroup addedFacilityGroup;
	private boolean success;

	public FacilityGroupAdded(FacilityGroup addedFacilityGroup, boolean success){
		this.addedFacilityGroup = addedFacilityGroup;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FacilityGroup getAddedFacilityGroup() {
		return addedFacilityGroup;
	}

}

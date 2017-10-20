package com.skytala.eCommerce.domain.product.relations.facility.event.groupRole;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.groupRole.FacilityGroupRole;
public class FacilityGroupRoleAdded implements Event{

	private FacilityGroupRole addedFacilityGroupRole;
	private boolean success;

	public FacilityGroupRoleAdded(FacilityGroupRole addedFacilityGroupRole, boolean success){
		this.addedFacilityGroupRole = addedFacilityGroupRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FacilityGroupRole getAddedFacilityGroupRole() {
		return addedFacilityGroupRole;
	}

}

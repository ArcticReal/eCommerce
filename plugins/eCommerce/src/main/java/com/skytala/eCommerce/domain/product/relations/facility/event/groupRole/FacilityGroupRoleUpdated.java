package com.skytala.eCommerce.domain.product.relations.facility.event.groupRole;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.groupRole.FacilityGroupRole;
public class FacilityGroupRoleUpdated implements Event{

	private boolean success;

	public FacilityGroupRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

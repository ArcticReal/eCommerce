package com.skytala.eCommerce.domain.product.relations.facilityGroupRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityGroupRole.model.FacilityGroupRole;
public class FacilityGroupRoleDeleted implements Event{

	private boolean success;

	public FacilityGroupRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

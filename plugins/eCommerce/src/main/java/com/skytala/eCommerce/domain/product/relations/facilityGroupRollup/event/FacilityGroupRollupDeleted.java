package com.skytala.eCommerce.domain.product.relations.facilityGroupRollup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityGroupRollup.model.FacilityGroupRollup;
public class FacilityGroupRollupDeleted implements Event{

	private boolean success;

	public FacilityGroupRollupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

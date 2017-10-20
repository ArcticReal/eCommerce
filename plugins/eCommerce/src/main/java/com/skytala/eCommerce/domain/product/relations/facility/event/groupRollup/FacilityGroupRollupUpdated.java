package com.skytala.eCommerce.domain.product.relations.facility.event.groupRollup;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.groupRollup.FacilityGroupRollup;
public class FacilityGroupRollupUpdated implements Event{

	private boolean success;

	public FacilityGroupRollupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

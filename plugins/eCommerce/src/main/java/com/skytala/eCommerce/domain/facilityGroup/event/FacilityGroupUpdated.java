package com.skytala.eCommerce.domain.facilityGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.facilityGroup.model.FacilityGroup;
public class FacilityGroupUpdated implements Event{

	private boolean success;

	public FacilityGroupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

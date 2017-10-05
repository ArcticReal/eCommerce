package com.skytala.eCommerce.domain.facilityType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.facilityType.model.FacilityType;
public class FacilityTypeDeleted implements Event{

	private boolean success;

	public FacilityTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

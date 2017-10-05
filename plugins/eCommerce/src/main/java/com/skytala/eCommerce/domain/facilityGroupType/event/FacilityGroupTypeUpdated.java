package com.skytala.eCommerce.domain.facilityGroupType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.facilityGroupType.model.FacilityGroupType;
public class FacilityGroupTypeUpdated implements Event{

	private boolean success;

	public FacilityGroupTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

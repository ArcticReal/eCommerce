package com.skytala.eCommerce.domain.facilityGroupType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.facilityGroupType.model.FacilityGroupType;
public class FacilityGroupTypeDeleted implements Event{

	private boolean success;

	public FacilityGroupTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

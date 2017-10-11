package com.skytala.eCommerce.domain.product.relations.facilityLocation.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityLocation.model.FacilityLocation;
public class FacilityLocationUpdated implements Event{

	private boolean success;

	public FacilityLocationUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

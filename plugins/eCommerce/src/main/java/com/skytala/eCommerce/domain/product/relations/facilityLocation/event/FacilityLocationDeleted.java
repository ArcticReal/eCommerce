package com.skytala.eCommerce.domain.product.relations.facilityLocation.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityLocation.model.FacilityLocation;
public class FacilityLocationDeleted implements Event{

	private boolean success;

	public FacilityLocationDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.product.relations.facility.event.location;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.location.FacilityLocation;
public class FacilityLocationDeleted implements Event{

	private boolean success;

	public FacilityLocationDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.product.relations.facility.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.Facility;
public class FacilityUpdated implements Event{

	private boolean success;

	public FacilityUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

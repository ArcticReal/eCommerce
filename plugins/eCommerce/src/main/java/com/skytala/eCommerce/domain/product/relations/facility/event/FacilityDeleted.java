package com.skytala.eCommerce.domain.product.relations.facility.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.Facility;
public class FacilityDeleted implements Event{

	private boolean success;

	public FacilityDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

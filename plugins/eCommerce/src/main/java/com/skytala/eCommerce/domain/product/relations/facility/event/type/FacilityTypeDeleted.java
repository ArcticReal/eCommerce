package com.skytala.eCommerce.domain.product.relations.facility.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.type.FacilityType;
public class FacilityTypeDeleted implements Event{

	private boolean success;

	public FacilityTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

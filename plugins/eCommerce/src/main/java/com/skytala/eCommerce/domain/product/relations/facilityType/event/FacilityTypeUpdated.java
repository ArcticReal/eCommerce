package com.skytala.eCommerce.domain.product.relations.facilityType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityType.model.FacilityType;
public class FacilityTypeUpdated implements Event{

	private boolean success;

	public FacilityTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

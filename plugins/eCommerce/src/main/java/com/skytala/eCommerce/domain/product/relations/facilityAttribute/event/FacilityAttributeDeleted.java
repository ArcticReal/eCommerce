package com.skytala.eCommerce.domain.product.relations.facilityAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityAttribute.model.FacilityAttribute;
public class FacilityAttributeDeleted implements Event{

	private boolean success;

	public FacilityAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

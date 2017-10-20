package com.skytala.eCommerce.domain.product.relations.facility.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.attribute.FacilityAttribute;
public class FacilityAttributeUpdated implements Event{

	private boolean success;

	public FacilityAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

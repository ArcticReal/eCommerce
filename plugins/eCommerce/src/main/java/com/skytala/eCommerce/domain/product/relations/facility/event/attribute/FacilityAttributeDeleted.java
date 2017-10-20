package com.skytala.eCommerce.domain.product.relations.facility.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.attribute.FacilityAttribute;
public class FacilityAttributeDeleted implements Event{

	private boolean success;

	public FacilityAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

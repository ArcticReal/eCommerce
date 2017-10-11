package com.skytala.eCommerce.domain.product.relations.facilityContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityContent.model.FacilityContent;
public class FacilityContentUpdated implements Event{

	private boolean success;

	public FacilityContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

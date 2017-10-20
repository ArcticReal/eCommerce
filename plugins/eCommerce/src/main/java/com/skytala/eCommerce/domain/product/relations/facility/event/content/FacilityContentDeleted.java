package com.skytala.eCommerce.domain.product.relations.facility.event.content;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.content.FacilityContent;
public class FacilityContentDeleted implements Event{

	private boolean success;

	public FacilityContentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

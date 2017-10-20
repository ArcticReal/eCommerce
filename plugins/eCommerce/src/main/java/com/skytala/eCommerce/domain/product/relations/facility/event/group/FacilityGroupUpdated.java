package com.skytala.eCommerce.domain.product.relations.facility.event.group;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.group.FacilityGroup;
public class FacilityGroupUpdated implements Event{

	private boolean success;

	public FacilityGroupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.product.relations.facilityGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityGroup.model.FacilityGroup;
public class FacilityGroupDeleted implements Event{

	private boolean success;

	public FacilityGroupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.product.relations.facilityGroupType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityGroupType.model.FacilityGroupType;
public class FacilityGroupTypeDeleted implements Event{

	private boolean success;

	public FacilityGroupTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

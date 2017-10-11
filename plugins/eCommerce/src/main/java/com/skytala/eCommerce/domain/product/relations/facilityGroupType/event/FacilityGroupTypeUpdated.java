package com.skytala.eCommerce.domain.product.relations.facilityGroupType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityGroupType.model.FacilityGroupType;
public class FacilityGroupTypeUpdated implements Event{

	private boolean success;

	public FacilityGroupTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

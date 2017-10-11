package com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.model.FacilityContactMechPurpose;
public class FacilityContactMechPurposeDeleted implements Event{

	private boolean success;

	public FacilityContactMechPurposeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

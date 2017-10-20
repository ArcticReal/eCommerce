package com.skytala.eCommerce.domain.product.relations.facility.event.contactMechPurpose;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.contactMechPurpose.FacilityContactMechPurpose;
public class FacilityContactMechPurposeDeleted implements Event{

	private boolean success;

	public FacilityContactMechPurposeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

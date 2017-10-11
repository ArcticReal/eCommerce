package com.skytala.eCommerce.domain.product.relations.facilityParty.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facilityParty.model.FacilityParty;
public class FacilityPartyUpdated implements Event{

	private boolean success;

	public FacilityPartyUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

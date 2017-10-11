package com.skytala.eCommerce.domain.party.relations.partyContactMechPurpose.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyContactMechPurpose.model.PartyContactMechPurpose;
public class PartyContactMechPurposeUpdated implements Event{

	private boolean success;

	public PartyContactMechPurposeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

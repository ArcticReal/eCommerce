package com.skytala.eCommerce.domain.party.relations.partyContactMechPurpose.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyContactMechPurpose.model.PartyContactMechPurpose;
public class PartyContactMechPurposeDeleted implements Event{

	private boolean success;

	public PartyContactMechPurposeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

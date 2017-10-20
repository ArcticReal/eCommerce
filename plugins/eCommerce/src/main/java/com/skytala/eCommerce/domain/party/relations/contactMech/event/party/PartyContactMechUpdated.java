package com.skytala.eCommerce.domain.party.relations.contactMech.event.party;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.party.PartyContactMech;
public class PartyContactMechUpdated implements Event{

	private boolean success;

	public PartyContactMechUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

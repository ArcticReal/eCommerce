package com.skytala.eCommerce.domain.party.relations.partyContactMech.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyContactMech.model.PartyContactMech;
public class PartyContactMechDeleted implements Event{

	private boolean success;

	public PartyContactMechDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

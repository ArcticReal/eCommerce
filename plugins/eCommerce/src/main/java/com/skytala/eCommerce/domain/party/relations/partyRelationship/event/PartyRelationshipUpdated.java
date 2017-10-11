package com.skytala.eCommerce.domain.party.relations.partyRelationship.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyRelationship.model.PartyRelationship;
public class PartyRelationshipUpdated implements Event{

	private boolean success;

	public PartyRelationshipUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.party.relations.partyRelationshipType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyRelationshipType.model.PartyRelationshipType;
public class PartyRelationshipTypeUpdated implements Event{

	private boolean success;

	public PartyRelationshipTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

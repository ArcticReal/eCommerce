package com.skytala.eCommerce.domain.party.relations.party.event.relationshipType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.relationshipType.PartyRelationshipType;
public class PartyRelationshipTypeDeleted implements Event{

	private boolean success;

	public PartyRelationshipTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

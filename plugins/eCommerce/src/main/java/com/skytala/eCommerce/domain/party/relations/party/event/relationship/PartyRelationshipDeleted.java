package com.skytala.eCommerce.domain.party.relations.party.event.relationship;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.relationship.PartyRelationship;
public class PartyRelationshipDeleted implements Event{

	private boolean success;

	public PartyRelationshipDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

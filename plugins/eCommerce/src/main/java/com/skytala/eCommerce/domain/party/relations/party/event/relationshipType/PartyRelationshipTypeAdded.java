package com.skytala.eCommerce.domain.party.relations.party.event.relationshipType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.relationshipType.PartyRelationshipType;
public class PartyRelationshipTypeAdded implements Event{

	private PartyRelationshipType addedPartyRelationshipType;
	private boolean success;

	public PartyRelationshipTypeAdded(PartyRelationshipType addedPartyRelationshipType, boolean success){
		this.addedPartyRelationshipType = addedPartyRelationshipType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyRelationshipType getAddedPartyRelationshipType() {
		return addedPartyRelationshipType;
	}

}

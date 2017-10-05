package com.skytala.eCommerce.domain.partyRelationshipType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.partyRelationshipType.model.PartyRelationshipType;
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

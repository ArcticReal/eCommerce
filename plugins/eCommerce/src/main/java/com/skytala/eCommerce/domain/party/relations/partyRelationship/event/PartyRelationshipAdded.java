package com.skytala.eCommerce.domain.party.relations.partyRelationship.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyRelationship.model.PartyRelationship;
public class PartyRelationshipAdded implements Event{

	private PartyRelationship addedPartyRelationship;
	private boolean success;

	public PartyRelationshipAdded(PartyRelationship addedPartyRelationship, boolean success){
		this.addedPartyRelationship = addedPartyRelationship;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyRelationship getAddedPartyRelationship() {
		return addedPartyRelationship;
	}

}

package com.skytala.eCommerce.domain.party.relations.party.event.relationshipType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.relationshipType.PartyRelationshipType;
public class PartyRelationshipTypeFound implements Event{

	private List<PartyRelationshipType> partyRelationshipTypes;

	public PartyRelationshipTypeFound(List<PartyRelationshipType> partyRelationshipTypes) {
		this.partyRelationshipTypes = partyRelationshipTypes;
	}

	public List<PartyRelationshipType> getPartyRelationshipTypes()	{
		return partyRelationshipTypes;
	}

}

package com.skytala.eCommerce.domain.party.relations.partyRelationship.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyRelationship.model.PartyRelationship;
public class PartyRelationshipFound implements Event{

	private List<PartyRelationship> partyRelationships;

	public PartyRelationshipFound(List<PartyRelationship> partyRelationships) {
		this.partyRelationships = partyRelationships;
	}

	public List<PartyRelationship> getPartyRelationships()	{
		return partyRelationships;
	}

}

package com.skytala.eCommerce.domain.party.relations.partyAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyAttribute.model.PartyAttribute;
public class PartyAttributeFound implements Event{

	private List<PartyAttribute> partyAttributes;

	public PartyAttributeFound(List<PartyAttribute> partyAttributes) {
		this.partyAttributes = partyAttributes;
	}

	public List<PartyAttribute> getPartyAttributes()	{
		return partyAttributes;
	}

}

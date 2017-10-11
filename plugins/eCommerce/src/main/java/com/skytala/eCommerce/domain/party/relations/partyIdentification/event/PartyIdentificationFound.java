package com.skytala.eCommerce.domain.party.relations.partyIdentification.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyIdentification.model.PartyIdentification;
public class PartyIdentificationFound implements Event{

	private List<PartyIdentification> partyIdentifications;

	public PartyIdentificationFound(List<PartyIdentification> partyIdentifications) {
		this.partyIdentifications = partyIdentifications;
	}

	public List<PartyIdentification> getPartyIdentifications()	{
		return partyIdentifications;
	}

}

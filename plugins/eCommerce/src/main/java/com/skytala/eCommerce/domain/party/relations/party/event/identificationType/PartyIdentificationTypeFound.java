package com.skytala.eCommerce.domain.party.relations.party.event.identificationType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.identificationType.PartyIdentificationType;
public class PartyIdentificationTypeFound implements Event{

	private List<PartyIdentificationType> partyIdentificationTypes;

	public PartyIdentificationTypeFound(List<PartyIdentificationType> partyIdentificationTypes) {
		this.partyIdentificationTypes = partyIdentificationTypes;
	}

	public List<PartyIdentificationType> getPartyIdentificationTypes()	{
		return partyIdentificationTypes;
	}

}

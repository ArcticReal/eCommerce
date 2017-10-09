package com.skytala.eCommerce.domain.partyIdentificationType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.partyIdentificationType.model.PartyIdentificationType;
public class PartyIdentificationTypeFound implements Event{

	private List<PartyIdentificationType> partyIdentificationTypes;

	public PartyIdentificationTypeFound(List<PartyIdentificationType> partyIdentificationTypes) {
		this.partyIdentificationTypes = partyIdentificationTypes;
	}

	public List<PartyIdentificationType> getPartyIdentificationTypes()	{
		return partyIdentificationTypes;
	}

}
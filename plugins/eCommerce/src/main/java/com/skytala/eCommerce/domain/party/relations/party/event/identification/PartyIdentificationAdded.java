package com.skytala.eCommerce.domain.party.relations.party.event.identification;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.identification.PartyIdentification;
public class PartyIdentificationAdded implements Event{

	private PartyIdentification addedPartyIdentification;
	private boolean success;

	public PartyIdentificationAdded(PartyIdentification addedPartyIdentification, boolean success){
		this.addedPartyIdentification = addedPartyIdentification;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyIdentification getAddedPartyIdentification() {
		return addedPartyIdentification;
	}

}

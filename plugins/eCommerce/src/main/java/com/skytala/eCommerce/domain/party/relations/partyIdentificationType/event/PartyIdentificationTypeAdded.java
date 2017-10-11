package com.skytala.eCommerce.domain.party.relations.partyIdentificationType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyIdentificationType.model.PartyIdentificationType;
public class PartyIdentificationTypeAdded implements Event{

	private PartyIdentificationType addedPartyIdentificationType;
	private boolean success;

	public PartyIdentificationTypeAdded(PartyIdentificationType addedPartyIdentificationType, boolean success){
		this.addedPartyIdentificationType = addedPartyIdentificationType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyIdentificationType getAddedPartyIdentificationType() {
		return addedPartyIdentificationType;
	}

}

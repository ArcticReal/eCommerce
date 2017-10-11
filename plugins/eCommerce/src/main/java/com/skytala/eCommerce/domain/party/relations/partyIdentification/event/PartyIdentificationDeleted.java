package com.skytala.eCommerce.domain.party.relations.partyIdentification.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyIdentification.model.PartyIdentification;
public class PartyIdentificationDeleted implements Event{

	private boolean success;

	public PartyIdentificationDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.party.relations.partyIdentificationType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyIdentificationType.model.PartyIdentificationType;
public class PartyIdentificationTypeDeleted implements Event{

	private boolean success;

	public PartyIdentificationTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

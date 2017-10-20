package com.skytala.eCommerce.domain.party.relations.party.event.identificationType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.identificationType.PartyIdentificationType;
public class PartyIdentificationTypeUpdated implements Event{

	private boolean success;

	public PartyIdentificationTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

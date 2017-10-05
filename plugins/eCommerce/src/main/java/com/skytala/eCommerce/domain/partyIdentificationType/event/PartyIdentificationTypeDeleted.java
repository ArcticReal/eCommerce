package com.skytala.eCommerce.domain.partyIdentificationType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.partyIdentificationType.model.PartyIdentificationType;
public class PartyIdentificationTypeDeleted implements Event{

	private boolean success;

	public PartyIdentificationTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

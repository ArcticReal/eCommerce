package com.skytala.eCommerce.domain.party.relations.communicationEvent.event.purpose;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.purpose.CommunicationEventPurpose;
public class CommunicationEventPurposeDeleted implements Event{

	private boolean success;

	public CommunicationEventPurposeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

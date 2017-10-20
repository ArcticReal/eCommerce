package com.skytala.eCommerce.domain.party.relations.communicationEvent.event.purpose;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.purpose.CommunicationEventPurpose;
public class CommunicationEventPurposeUpdated implements Event{

	private boolean success;

	public CommunicationEventPurposeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.party.relations.communicationEvent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.CommunicationEvent;
public class CommunicationEventUpdated implements Event{

	private boolean success;

	public CommunicationEventUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

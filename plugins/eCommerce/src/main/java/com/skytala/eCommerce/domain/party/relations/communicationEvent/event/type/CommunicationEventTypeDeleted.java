package com.skytala.eCommerce.domain.party.relations.communicationEvent.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.type.CommunicationEventType;
public class CommunicationEventTypeDeleted implements Event{

	private boolean success;

	public CommunicationEventTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

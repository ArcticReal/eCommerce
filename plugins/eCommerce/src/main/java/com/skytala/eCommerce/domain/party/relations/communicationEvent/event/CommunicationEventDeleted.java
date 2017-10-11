package com.skytala.eCommerce.domain.party.relations.communicationEvent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.CommunicationEvent;
public class CommunicationEventDeleted implements Event{

	private boolean success;

	public CommunicationEventDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

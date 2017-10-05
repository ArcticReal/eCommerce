package com.skytala.eCommerce.domain.communicationEvent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.communicationEvent.model.CommunicationEvent;
public class CommunicationEventDeleted implements Event{

	private boolean success;

	public CommunicationEventDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

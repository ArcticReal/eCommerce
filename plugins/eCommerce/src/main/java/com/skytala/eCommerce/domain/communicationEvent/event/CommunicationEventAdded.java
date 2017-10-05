package com.skytala.eCommerce.domain.communicationEvent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.communicationEvent.model.CommunicationEvent;
public class CommunicationEventAdded implements Event{

	private CommunicationEvent addedCommunicationEvent;
	private boolean success;

	public CommunicationEventAdded(CommunicationEvent addedCommunicationEvent, boolean success){
		this.addedCommunicationEvent = addedCommunicationEvent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CommunicationEvent getAddedCommunicationEvent() {
		return addedCommunicationEvent;
	}

}

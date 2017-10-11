package com.skytala.eCommerce.domain.party.relations.communicationEventType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEventType.model.CommunicationEventType;
public class CommunicationEventTypeAdded implements Event{

	private CommunicationEventType addedCommunicationEventType;
	private boolean success;

	public CommunicationEventTypeAdded(CommunicationEventType addedCommunicationEventType, boolean success){
		this.addedCommunicationEventType = addedCommunicationEventType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CommunicationEventType getAddedCommunicationEventType() {
		return addedCommunicationEventType;
	}

}

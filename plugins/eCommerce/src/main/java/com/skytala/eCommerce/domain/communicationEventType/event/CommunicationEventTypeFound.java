package com.skytala.eCommerce.domain.communicationEventType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.communicationEventType.model.CommunicationEventType;
public class CommunicationEventTypeFound implements Event{

	private List<CommunicationEventType> communicationEventTypes;

	public CommunicationEventTypeFound(List<CommunicationEventType> communicationEventTypes) {
		this.communicationEventTypes = communicationEventTypes;
	}

	public List<CommunicationEventType> getCommunicationEventTypes()	{
		return communicationEventTypes;
	}

}
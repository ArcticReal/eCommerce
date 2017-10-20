package com.skytala.eCommerce.domain.party.relations.communicationEvent.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.type.CommunicationEventType;
public class CommunicationEventTypeFound implements Event{

	private List<CommunicationEventType> communicationEventTypes;

	public CommunicationEventTypeFound(List<CommunicationEventType> communicationEventTypes) {
		this.communicationEventTypes = communicationEventTypes;
	}

	public List<CommunicationEventType> getCommunicationEventTypes()	{
		return communicationEventTypes;
	}

}

package com.skytala.eCommerce.domain.party.relations.communicationEvent.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.CommunicationEvent;
public class CommunicationEventFound implements Event{

	private List<CommunicationEvent> communicationEvents;

	public CommunicationEventFound(List<CommunicationEvent> communicationEvents) {
		this.communicationEvents = communicationEvents;
	}

	public List<CommunicationEvent> getCommunicationEvents()	{
		return communicationEvents;
	}

}

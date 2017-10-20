package com.skytala.eCommerce.domain.party.relations.communicationEvent.event.purpose;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.purpose.CommunicationEventPurpose;
public class CommunicationEventPurposeFound implements Event{

	private List<CommunicationEventPurpose> communicationEventPurposes;

	public CommunicationEventPurposeFound(List<CommunicationEventPurpose> communicationEventPurposes) {
		this.communicationEventPurposes = communicationEventPurposes;
	}

	public List<CommunicationEventPurpose> getCommunicationEventPurposes()	{
		return communicationEventPurposes;
	}

}

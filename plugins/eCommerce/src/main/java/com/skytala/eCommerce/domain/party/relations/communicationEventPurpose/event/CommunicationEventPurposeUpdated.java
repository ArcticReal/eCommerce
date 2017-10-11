package com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.model.CommunicationEventPurpose;
public class CommunicationEventPurposeUpdated implements Event{

	private boolean success;

	public CommunicationEventPurposeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

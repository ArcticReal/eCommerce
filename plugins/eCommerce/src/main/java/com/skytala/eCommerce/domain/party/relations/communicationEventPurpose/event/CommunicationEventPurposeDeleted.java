package com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.model.CommunicationEventPurpose;
public class CommunicationEventPurposeDeleted implements Event{

	private boolean success;

	public CommunicationEventPurposeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

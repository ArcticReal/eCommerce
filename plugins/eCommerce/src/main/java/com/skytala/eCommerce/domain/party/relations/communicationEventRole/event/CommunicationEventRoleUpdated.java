package com.skytala.eCommerce.domain.party.relations.communicationEventRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEventRole.model.CommunicationEventRole;
public class CommunicationEventRoleUpdated implements Event{

	private boolean success;

	public CommunicationEventRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

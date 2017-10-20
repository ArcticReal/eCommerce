package com.skytala.eCommerce.domain.party.relations.communicationEvent.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.role.CommunicationEventRole;
public class CommunicationEventRoleDeleted implements Event{

	private boolean success;

	public CommunicationEventRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.party.relations.communicationEventRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEventRole.model.CommunicationEventRole;
public class CommunicationEventRoleAdded implements Event{

	private CommunicationEventRole addedCommunicationEventRole;
	private boolean success;

	public CommunicationEventRoleAdded(CommunicationEventRole addedCommunicationEventRole, boolean success){
		this.addedCommunicationEventRole = addedCommunicationEventRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CommunicationEventRole getAddedCommunicationEventRole() {
		return addedCommunicationEventRole;
	}

}

package com.skytala.eCommerce.domain.party.relations.communicationEvent.event.role;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.role.CommunicationEventRole;
public class CommunicationEventRoleFound implements Event{

	private List<CommunicationEventRole> communicationEventRoles;

	public CommunicationEventRoleFound(List<CommunicationEventRole> communicationEventRoles) {
		this.communicationEventRoles = communicationEventRoles;
	}

	public List<CommunicationEventRole> getCommunicationEventRoles()	{
		return communicationEventRoles;
	}

}

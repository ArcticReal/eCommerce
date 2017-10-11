package com.skytala.eCommerce.domain.party.relations.partyInvitationRoleAssoc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyInvitationRoleAssoc.model.PartyInvitationRoleAssoc;
public class PartyInvitationRoleAssocUpdated implements Event{

	private boolean success;

	public PartyInvitationRoleAssocUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

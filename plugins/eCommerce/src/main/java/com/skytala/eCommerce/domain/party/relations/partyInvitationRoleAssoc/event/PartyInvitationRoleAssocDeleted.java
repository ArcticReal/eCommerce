package com.skytala.eCommerce.domain.party.relations.partyInvitationRoleAssoc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyInvitationRoleAssoc.model.PartyInvitationRoleAssoc;
public class PartyInvitationRoleAssocDeleted implements Event{

	private boolean success;

	public PartyInvitationRoleAssocDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

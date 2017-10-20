package com.skytala.eCommerce.domain.party.relations.party.event.invitationRoleAssoc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.invitationRoleAssoc.PartyInvitationRoleAssoc;
public class PartyInvitationRoleAssocDeleted implements Event{

	private boolean success;

	public PartyInvitationRoleAssocDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

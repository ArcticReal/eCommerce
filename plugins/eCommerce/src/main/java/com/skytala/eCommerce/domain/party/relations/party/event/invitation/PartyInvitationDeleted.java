package com.skytala.eCommerce.domain.party.relations.party.event.invitation;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.invitation.PartyInvitation;
public class PartyInvitationDeleted implements Event{

	private boolean success;

	public PartyInvitationDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

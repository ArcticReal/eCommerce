package com.skytala.eCommerce.domain.partyInvitation.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.partyInvitation.model.PartyInvitation;
public class PartyInvitationUpdated implements Event{

	private boolean success;

	public PartyInvitationUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

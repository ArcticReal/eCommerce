package com.skytala.eCommerce.domain.party.relations.party.event.invitationGroupAssoc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.invitationGroupAssoc.PartyInvitationGroupAssoc;
public class PartyInvitationGroupAssocUpdated implements Event{

	private boolean success;

	public PartyInvitationGroupAssocUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

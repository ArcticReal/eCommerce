package com.skytala.eCommerce.domain.party.relations.partyInvitationGroupAssoc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyInvitationGroupAssoc.model.PartyInvitationGroupAssoc;
public class PartyInvitationGroupAssocUpdated implements Event{

	private boolean success;

	public PartyInvitationGroupAssocUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

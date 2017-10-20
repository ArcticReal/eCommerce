package com.skytala.eCommerce.domain.party.relations.party.event.invitationGroupAssoc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.invitationGroupAssoc.PartyInvitationGroupAssoc;
public class PartyInvitationGroupAssocDeleted implements Event{

	private boolean success;

	public PartyInvitationGroupAssocDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

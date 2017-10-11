package com.skytala.eCommerce.domain.party.relations.partyRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyRole.model.PartyRole;
public class PartyRoleUpdated implements Event{

	private boolean success;

	public PartyRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

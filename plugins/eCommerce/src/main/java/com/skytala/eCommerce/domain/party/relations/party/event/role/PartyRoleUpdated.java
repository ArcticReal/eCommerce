package com.skytala.eCommerce.domain.party.relations.party.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.role.PartyRole;
public class PartyRoleUpdated implements Event{

	private boolean success;

	public PartyRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

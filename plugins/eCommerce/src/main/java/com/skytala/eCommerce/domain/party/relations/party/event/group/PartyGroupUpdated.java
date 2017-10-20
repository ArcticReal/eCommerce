package com.skytala.eCommerce.domain.party.relations.party.event.group;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.group.PartyGroup;
public class PartyGroupUpdated implements Event{

	private boolean success;

	public PartyGroupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

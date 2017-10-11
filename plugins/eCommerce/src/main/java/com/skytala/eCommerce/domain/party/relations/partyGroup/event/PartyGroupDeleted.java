package com.skytala.eCommerce.domain.party.relations.partyGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyGroup.model.PartyGroup;
public class PartyGroupDeleted implements Event{

	private boolean success;

	public PartyGroupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

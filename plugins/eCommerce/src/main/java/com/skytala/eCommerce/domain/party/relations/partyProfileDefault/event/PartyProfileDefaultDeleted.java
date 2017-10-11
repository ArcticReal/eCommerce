package com.skytala.eCommerce.domain.party.relations.partyProfileDefault.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyProfileDefault.model.PartyProfileDefault;
public class PartyProfileDefaultDeleted implements Event{

	private boolean success;

	public PartyProfileDefaultDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

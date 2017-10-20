package com.skytala.eCommerce.domain.party.relations.party.event.profileDefault;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.profileDefault.PartyProfileDefault;
public class PartyProfileDefaultDeleted implements Event{

	private boolean success;

	public PartyProfileDefaultDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

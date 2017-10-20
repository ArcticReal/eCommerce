package com.skytala.eCommerce.domain.party.relations.party.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.type.PartyType;
public class PartyTypeDeleted implements Event{

	private boolean success;

	public PartyTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

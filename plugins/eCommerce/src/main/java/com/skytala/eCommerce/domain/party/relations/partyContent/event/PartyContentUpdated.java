package com.skytala.eCommerce.domain.party.relations.partyContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyContent.model.PartyContent;
public class PartyContentUpdated implements Event{

	private boolean success;

	public PartyContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

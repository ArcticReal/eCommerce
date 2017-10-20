package com.skytala.eCommerce.domain.party.relations.party.event.content;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.content.PartyContent;
public class PartyContentUpdated implements Event{

	private boolean success;

	public PartyContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

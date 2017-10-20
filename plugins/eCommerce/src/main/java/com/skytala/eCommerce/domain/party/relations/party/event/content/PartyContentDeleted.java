package com.skytala.eCommerce.domain.party.relations.party.event.content;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.content.PartyContent;
public class PartyContentDeleted implements Event{

	private boolean success;

	public PartyContentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

package com.skytala.eCommerce.domain.party.relations.partyContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyContentType.model.PartyContentType;
public class PartyContentTypeDeleted implements Event{

	private boolean success;

	public PartyContentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

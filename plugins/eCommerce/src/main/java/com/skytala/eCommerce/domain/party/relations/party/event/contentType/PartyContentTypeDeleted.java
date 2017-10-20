package com.skytala.eCommerce.domain.party.relations.party.event.contentType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.contentType.PartyContentType;
public class PartyContentTypeDeleted implements Event{

	private boolean success;

	public PartyContentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

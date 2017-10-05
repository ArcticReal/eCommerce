package com.skytala.eCommerce.domain.partyContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.partyContentType.model.PartyContentType;
public class PartyContentTypeUpdated implements Event{

	private boolean success;

	public PartyContentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

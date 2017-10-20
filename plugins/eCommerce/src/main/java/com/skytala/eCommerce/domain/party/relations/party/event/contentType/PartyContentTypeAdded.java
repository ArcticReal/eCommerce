package com.skytala.eCommerce.domain.party.relations.party.event.contentType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.contentType.PartyContentType;
public class PartyContentTypeAdded implements Event{

	private PartyContentType addedPartyContentType;
	private boolean success;

	public PartyContentTypeAdded(PartyContentType addedPartyContentType, boolean success){
		this.addedPartyContentType = addedPartyContentType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyContentType getAddedPartyContentType() {
		return addedPartyContentType;
	}

}

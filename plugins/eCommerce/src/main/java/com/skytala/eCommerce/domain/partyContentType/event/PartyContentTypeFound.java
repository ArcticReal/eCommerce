package com.skytala.eCommerce.domain.partyContentType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.partyContentType.model.PartyContentType;
public class PartyContentTypeFound implements Event{

	private List<PartyContentType> partyContentTypes;

	public PartyContentTypeFound(List<PartyContentType> partyContentTypes) {
		this.partyContentTypes = partyContentTypes;
	}

	public List<PartyContentType> getPartyContentTypes()	{
		return partyContentTypes;
	}

}

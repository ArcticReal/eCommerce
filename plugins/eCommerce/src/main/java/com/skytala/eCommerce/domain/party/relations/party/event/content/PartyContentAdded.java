package com.skytala.eCommerce.domain.party.relations.party.event.content;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.content.PartyContent;
public class PartyContentAdded implements Event{

	private PartyContent addedPartyContent;
	private boolean success;

	public PartyContentAdded(PartyContent addedPartyContent, boolean success){
		this.addedPartyContent = addedPartyContent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyContent getAddedPartyContent() {
		return addedPartyContent;
	}

}

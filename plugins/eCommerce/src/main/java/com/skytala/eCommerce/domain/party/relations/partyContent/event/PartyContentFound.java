package com.skytala.eCommerce.domain.party.relations.partyContent.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyContent.model.PartyContent;
public class PartyContentFound implements Event{

	private List<PartyContent> partyContents;

	public PartyContentFound(List<PartyContent> partyContents) {
		this.partyContents = partyContents;
	}

	public List<PartyContent> getPartyContents()	{
		return partyContents;
	}

}

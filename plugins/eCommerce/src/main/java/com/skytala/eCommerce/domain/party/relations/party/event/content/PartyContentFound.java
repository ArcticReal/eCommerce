package com.skytala.eCommerce.domain.party.relations.party.event.content;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.content.PartyContent;
public class PartyContentFound implements Event{

	private List<PartyContent> partyContents;

	public PartyContentFound(List<PartyContent> partyContents) {
		this.partyContents = partyContents;
	}

	public List<PartyContent> getPartyContents()	{
		return partyContents;
	}

}

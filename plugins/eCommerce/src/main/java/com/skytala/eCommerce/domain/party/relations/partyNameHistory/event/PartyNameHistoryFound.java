package com.skytala.eCommerce.domain.party.relations.partyNameHistory.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyNameHistory.model.PartyNameHistory;
public class PartyNameHistoryFound implements Event{

	private List<PartyNameHistory> partyNameHistorys;

	public PartyNameHistoryFound(List<PartyNameHistory> partyNameHistorys) {
		this.partyNameHistorys = partyNameHistorys;
	}

	public List<PartyNameHistory> getPartyNameHistorys()	{
		return partyNameHistorys;
	}

}

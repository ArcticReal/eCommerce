package com.skytala.eCommerce.domain.party.relations.party.event.nameHistory;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.nameHistory.PartyNameHistory;
public class PartyNameHistoryFound implements Event{

	private List<PartyNameHistory> partyNameHistorys;

	public PartyNameHistoryFound(List<PartyNameHistory> partyNameHistorys) {
		this.partyNameHistorys = partyNameHistorys;
	}

	public List<PartyNameHistory> getPartyNameHistorys()	{
		return partyNameHistorys;
	}

}

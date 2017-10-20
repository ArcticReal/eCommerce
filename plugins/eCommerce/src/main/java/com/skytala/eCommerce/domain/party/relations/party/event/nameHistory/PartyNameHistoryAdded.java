package com.skytala.eCommerce.domain.party.relations.party.event.nameHistory;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.nameHistory.PartyNameHistory;
public class PartyNameHistoryAdded implements Event{

	private PartyNameHistory addedPartyNameHistory;
	private boolean success;

	public PartyNameHistoryAdded(PartyNameHistory addedPartyNameHistory, boolean success){
		this.addedPartyNameHistory = addedPartyNameHistory;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyNameHistory getAddedPartyNameHistory() {
		return addedPartyNameHistory;
	}

}

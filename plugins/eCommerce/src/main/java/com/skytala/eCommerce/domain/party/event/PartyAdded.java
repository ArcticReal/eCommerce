package com.skytala.eCommerce.domain.party.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.model.Party;
public class PartyAdded implements Event{

	private Party addedParty;
	private boolean success;

	public PartyAdded(Party addedParty, boolean success){
		this.addedParty = addedParty;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Party getAddedParty() {
		return addedParty;
	}

}

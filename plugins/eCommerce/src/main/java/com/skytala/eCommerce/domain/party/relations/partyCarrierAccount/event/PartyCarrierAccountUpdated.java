package com.skytala.eCommerce.domain.party.relations.partyCarrierAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyCarrierAccount.model.PartyCarrierAccount;
public class PartyCarrierAccountUpdated implements Event{

	private boolean success;

	public PartyCarrierAccountUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

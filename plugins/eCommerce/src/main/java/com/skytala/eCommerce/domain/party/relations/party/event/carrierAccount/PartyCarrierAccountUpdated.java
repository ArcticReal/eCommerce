package com.skytala.eCommerce.domain.party.relations.party.event.carrierAccount;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.carrierAccount.PartyCarrierAccount;
public class PartyCarrierAccountUpdated implements Event{

	private boolean success;

	public PartyCarrierAccountUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

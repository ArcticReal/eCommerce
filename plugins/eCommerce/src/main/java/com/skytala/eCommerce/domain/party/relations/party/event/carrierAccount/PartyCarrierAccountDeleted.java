package com.skytala.eCommerce.domain.party.relations.party.event.carrierAccount;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.carrierAccount.PartyCarrierAccount;
public class PartyCarrierAccountDeleted implements Event{

	private boolean success;

	public PartyCarrierAccountDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

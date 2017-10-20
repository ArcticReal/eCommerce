package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.party;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.party.PartyGlAccount;
public class PartyGlAccountUpdated implements Event{

	private boolean success;

	public PartyGlAccountUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}

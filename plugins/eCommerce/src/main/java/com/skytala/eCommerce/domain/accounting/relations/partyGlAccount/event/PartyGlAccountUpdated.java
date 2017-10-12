package com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.model.PartyGlAccount;
public class PartyGlAccountUpdated implements Event{

	private boolean success;

	public PartyGlAccountUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
